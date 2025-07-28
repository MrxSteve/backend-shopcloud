package com.shopcloud.user_mservice.infra.adapters.out.sql.seed;

import com.shopcloud.user_mservice.infra.adapters.out.sql.entity.RoleEntity;
import com.shopcloud.user_mservice.infra.adapters.out.sql.repository.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationRunner{
    private final RoleJpaRepository roleJpaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> defaultRoles = List.of("ROLE_ADMIN", "ROLE_CLIENT", "ROLE_STAFF");

        defaultRoles.forEach(roleName -> {
            if (!roleJpaRepository.existsByName(roleName)) {
                RoleEntity role = new RoleEntity();
                role.setName(roleName);
                roleJpaRepository.save(role);
                log.info("Role '{}' has been created.", roleName);
            } else {
                log.info("Role '{}' already exists, skipping creation.", roleName);
            }
        });
    }
}
