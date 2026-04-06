package com.debasish.deployment_portal_service.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String serviceName;
    private String teamName;
    private String repoUrl;
}