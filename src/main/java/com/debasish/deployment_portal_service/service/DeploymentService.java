package com.debasish.deployment_portal_service.service;

import java.io.File;
import java.io.FileWriter;

import org.springframework.stereotype.Service;

import com.debasish.deployment_portal_service.dto.RegisterRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeploymentService {

    public void generateFiles(RegisterRequest request) {
        try {
            String basePath = "generated/" + request.getServiceName();

            new File(basePath + "/terraform").mkdirs();
            new File(basePath + "/k8s").mkdirs();
            new File(basePath + "/ci").mkdirs();

            generateTerraform(request, basePath);
            generateK8s(request, basePath);
            generateCI(request, basePath);

        } catch (Exception e) {
            log.error("Error generating files", e);
            throw new RuntimeException(e);
        }
    }

    private void generateTerraform(RegisterRequest req, String basePath) throws Exception {

        String ecr = """
        resource "aws_ecr_repository" "repo" {
          name = "%s"
        }
        """.formatted(req.getServiceName());

        write(basePath + "/terraform/ecr.tf", ecr);

        String iam = """
        resource "aws_iam_role" "role" {
          name = "%s-role"
        }
        """.formatted(req.getServiceName());

        write(basePath + "/terraform/iam.tf", iam);
    }

    private void generateK8s(RegisterRequest req, String basePath) throws Exception {

        String yaml = """
        apiVersion: apps/v1
        kind: Deployment
        metadata:
          name: %s
        spec:
          replicas: 1
        """.formatted(req.getServiceName());

        write(basePath + "/k8s/deployment.yaml", yaml);
    }

    private void generateCI(RegisterRequest req, String basePath) throws Exception {

        String jenkins = """
        pipeline {
          stages {
            stage('Build') {
              steps {
                echo 'Building %s'
              }
            }
          }
        }
        """.formatted(req.getServiceName());

        write(basePath + "/ci/Jenkinsfile", jenkins);
    }

    private void write(String path, String content) throws Exception {
        FileWriter writer = new FileWriter(path);
        writer.write(content);
        writer.close();
    }
}