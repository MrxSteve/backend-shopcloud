package com.shopcloud.user_mservice.infra.adapters.out.sql.mapper;

import com.shopcloud.user_mservice.domain.models.RoleModel;
import com.shopcloud.user_mservice.infra.adapters.out.sql.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    RoleModel toModel(RoleEntity roleEntity);
    //@Mapping(target = "id", ignore = true)
    RoleEntity toEntity(RoleModel roleModel);
}
