package com.shopcloud.user_mservice.infra.adapters.in.rest.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class RoleRequest {
     @NotBlank(message = "Role name cannot be blank")
     @Size(min = 3, max = 30, message = "Role name must be between 3 and 30 characters")
    private String name;
}
