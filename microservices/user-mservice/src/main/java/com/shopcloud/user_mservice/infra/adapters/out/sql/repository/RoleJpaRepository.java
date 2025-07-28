package com.shopcloud.user_mservice.infra.adapters.out.sql.repository;

import com.shopcloud.user_mservice.infra.adapters.out.sql.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, UUID> {
    boolean existsByName(String name);
    Page<RoleEntity> findAll(Pageable pageable);
    Optional<RoleEntity> findByNameIgnoreCase(String name);
}
