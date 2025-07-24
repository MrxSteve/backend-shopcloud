package com.shopcloud.notification_mservice.infra.adapters.in.rabbit.dto;

import lombok.*;

import java.util.Map;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class EmailMessageDTO {
    private String to;
    private String templateCode;
    private Map<String, String> variables;
}
