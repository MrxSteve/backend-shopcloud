package com.shopcloud.user_mservice.application.ports.out;

import com.shopcloud.user_mservice.domain.models.RoleModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolePersistencePort {
    RoleModel save(RoleModel role);
    Optional<RoleModel> findById(UUID id);
    Optional<RoleModel> findByName(String name);
    boolean existsByName(String name);
    void deleteById(UUID id);
    List<RoleModel> findAll(int page, int size);
}
