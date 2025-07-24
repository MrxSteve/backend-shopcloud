package com.shopcloud.notification_mservice.infra.adapters.out.sql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
@Entity
@Table(name = "email_logs")
public class EmailLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "to_email", nullable = false, length = 150)
    private String toEmail;
    @Column(name = "subject", nullable = false, columnDefinition = "TEXT")
    private String subject;
    @Column(name = "body", columnDefinition = "TEXT")
    private String body;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @ManyToOne
    @JoinColumn(name = "template_code", referencedColumnName = "code", foreignKey = @ForeignKey(name = "fk_template_code"))
    private EmailTemplateEntity template;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}
