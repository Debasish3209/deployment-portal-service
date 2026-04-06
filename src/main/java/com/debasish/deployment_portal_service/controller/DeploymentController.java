package com.debasish.deployment_portal_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.debasish.deployment_portal_service.dto.RegisterRequest;
import com.debasish.deployment_portal_service.service.DeploymentService;

@RestController
@RequestMapping("/deploy")
@RequiredArgsConstructor
public class DeploymentController {

    private final DeploymentService deploymentService;

    @PostMapping("/register-service")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        deploymentService.generateFiles(request);
        return ResponseEntity.ok("Service setup generated successfully");
    }
}