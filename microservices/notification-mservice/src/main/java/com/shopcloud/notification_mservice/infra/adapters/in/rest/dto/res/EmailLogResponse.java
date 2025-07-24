package com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.res;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class EmailLogResponse {
    private UUID id;
    private String toEmail;
    private String subject;
    private String body;
    private String status;
    private String errorMessage;
    private String templateCode;
    private OffsetDateTime createdAt;
}
