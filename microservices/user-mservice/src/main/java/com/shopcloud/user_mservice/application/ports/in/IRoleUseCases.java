package com.shopcloud.user_mservice.application.ports.in;

import com.shopcloud.user_mservice.domain.models.RoleModel;

import java.util.List;
import java.util.UUID;

public interface IRoleUseCases {
    RoleModel createRole(RoleModel role);
    RoleModel getRoleById(UUID id);
    RoleModel getRoleByName(String name);
    void deleteRoleById(UUID id);
    List<RoleModel> getAllRoles(int page, int size);
}
