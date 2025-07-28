package com.shopcloud.user_mservice.infra.adapters.out.sql.adapter;

import com.shopcloud.user_mservice.application.ports.out.RolePersistencePort;
import com.shopcloud.user_mservice.domain.models.RoleModel;
import com.shopcloud.user_mservice.infra.adapters.out.sql.entity.RoleEntity;
import com.shopcloud.user_mservice.infra.adapters.out.sql.mapper.RoleEntityMapper;
import com.shopcloud.user_mservice.infra.adapters.out.sql.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleJpaAdapter implements RolePersistencePort {
    private final RoleJpaRepository roleJpaRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public RoleModel save(RoleModel role) {
        RoleEntity entity = roleEntityMapper.toEntity(role);
        RoleEntity savedEntity = roleJpaRepository.save(entity);
        return roleEntityMapper.toModel(savedEntity);
    }

    @Override
    public Optional<RoleModel> findById(UUID id) {
        return roleJpaRepository.findById(id)
                .map(roleEntityMapper::toModel);
    }

    @Override
    public Optional<RoleModel> findByName(String name) {
        return roleJpaRepository.findByNameIgnoreCase(name)
                .map(roleEntityMapper::toModel);
    }

    @Override
    public boolean existsByName(String name) {
        return roleJpaRepository.existsByName(name);
    }

    @Override
    public void deleteById(UUID id) {
        this.findById(id);
        roleJpaRepository.deleteById(id);
    }

    @Override
    public List<RoleModel> findAll(int page, int size) {
        return roleJpaRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(roleEntityMapper::toModel)
                .toList();
    }
}
