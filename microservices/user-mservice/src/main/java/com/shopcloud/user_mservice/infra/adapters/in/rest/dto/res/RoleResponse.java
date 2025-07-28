package com.shopcloud.user_mservice.infra.adapters.in.rest.dto.res;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoleResponse {
    private UUID id;
    private String name;
}
