package com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class CreateEmailTemplate {
    @NotBlank(message = "Code is required")
    @Size(min = 3, max = 50, message = "Code must be between 3 and 50 characters")
    private String code;
    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;
    @NotBlank(message = "Subject template is required")
    @Size(min = 3, max = 255, message = "Subject template must be between 3 and 255 characters")
    private String subjectTemplate;
    @NotBlank(message = "Body template is required")
    @Size(min = 3, max = 1000, message = "Body template must be between 3 and 1000 characters")
    private String bodyTemplate;
}
