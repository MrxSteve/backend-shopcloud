package com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class CreateEmailLogRequest {
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email format")
    private String toEmail;
    @NotBlank(message = "Subject is required")
    private String subject;
    private String body;
    @NotBlank(message = "Status is required")
    private String status;
    private String templateCode;
}
