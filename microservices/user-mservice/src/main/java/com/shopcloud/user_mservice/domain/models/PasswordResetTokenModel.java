package com.shopcloud.user_mservice.domain.models;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class PasswordResetTokenModel {
    private UUID userId;
    private String token;
    private OffsetDateTime expiresAt;
}
