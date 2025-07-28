package com.shopcloud.user_mservice.application.usecases;

import com.shopcloud.user_mservice.application.ports.in.IRoleUseCases;
import com.shopcloud.user_mservice.application.ports.out.RolePersistencePort;
import com.shopcloud.user_mservice.domain.models.RoleModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class RoleService implements IRoleUseCases {
    private final RolePersistencePort rolePersistencePort;

    @Override
    public RoleModel createRole(RoleModel role) {
        if (rolePersistencePort.existsByName(role.getName())) {
            throw new RuntimeException("Role with name '" + role.getName() + "' already exists.");
        }
        return rolePersistencePort.save(role);
    }

    @Override
    public RoleModel getRoleById(UUID id) {
        return rolePersistencePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Role with id '" + id + "' not found."));
    }

    @Override
    public RoleModel getRoleByName(String name) {
        return rolePersistencePort.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role with name '" + name + "' not found."));
    }

    @Override
    public void deleteRoleById(UUID id) {
        this.getRoleById(id);
        rolePersistencePort.deleteById(id);
    }

    @Override
    public List<RoleModel> getAllRoles(int page, int size) {
        return rolePersistencePort.findAll(page, size);
    }
}
