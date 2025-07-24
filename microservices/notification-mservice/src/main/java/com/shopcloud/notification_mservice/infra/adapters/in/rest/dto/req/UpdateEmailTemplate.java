package com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req;

import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class UpdateEmailTemplate {
    @Size(max = 50, message = "Code must be between 0 and 50 characters")
    private String code;
    @Size(max = 255, message = "Description must be between 0 and 255 characters")
    private String description;
    @Size(max = 255, message = "Subject template must be between 0 and 255 characters")
    private String subjectTemplate;
    @Size(max = 1000, message = "Body template must be between 0 and 1000 characters")
    private String bodyTemplate;
}
