package com.shopcloud.user_mservice.domain.models;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class UserProfileModel {
    private UUID userId;
    private String fullName;
    private String phone;
    private String address;
    private String avatarUrl;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
