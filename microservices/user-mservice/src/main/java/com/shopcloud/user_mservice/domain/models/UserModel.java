package com.shopcloud.user_mservice.domain.models;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class UserModel {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private Boolean enabled;
    private String provider;

    private UserProfileModel profile;
    private Set<RoleModel> roles;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
