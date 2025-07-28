package com.shopcloud.user_mservice.infra.adapters.in.rest.mapper;

import com.shopcloud.user_mservice.domain.models.RoleModel;
import com.shopcloud.user_mservice.infra.adapters.in.rest.dto.req.RoleRequest;
import com.shopcloud.user_mservice.infra.adapters.in.rest.dto.res.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleRestMapper {
    //@Mapping(target = "id", ignore = true)
    RoleModel toModel(RoleRequest request);
    RoleResponse toResponse(RoleModel model);
}
