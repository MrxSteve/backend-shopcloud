package com.shopcloud.notification_mservice.domain.models;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class EmailTemplateModel {
    private UUID id;
    private String code;
    private String description;
    private String subjectTemplate;
    private String bodyTemplate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
