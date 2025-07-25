package com.shopcloud.user_mservice.infra.adapters.out.sql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
@Entity
@Table(name = "account_activation_tokens")
public class AccountActivationTokenEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "uuid")
    private UUID userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String token;

    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;
}
