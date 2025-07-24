package com.shopcloud.notification_mservice.domain.models;

import lombok.*;

import java.util.Map;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class EmailMessageModel {
    private String to;
    private String templateCode;
    private Map<String, String> variables;
}
