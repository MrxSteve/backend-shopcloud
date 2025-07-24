package com.shopcloud.notification_mservice.domain.models;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class EmailLogModel {
    private UUID id;
    private String toEmail;
    private String subject;
    private String body;
    private String status;
    private String errorMessage;
    private String templateCode;
    private OffsetDateTime createdAt;
}
