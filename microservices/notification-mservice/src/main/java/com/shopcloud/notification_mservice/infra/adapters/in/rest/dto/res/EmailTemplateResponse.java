package com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.res;


import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class EmailTemplateResponse {
    private UUID id;
    private String code;
    private String description;
    private String subjectTemplate;
    private String bodyTemplate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
