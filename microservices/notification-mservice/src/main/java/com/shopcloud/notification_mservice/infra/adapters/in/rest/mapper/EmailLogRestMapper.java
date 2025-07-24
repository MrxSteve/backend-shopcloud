package com.shopcloud.notification_mservice.infra.adapters.in.rest.mapper;

import com.shopcloud.notification_mservice.domain.models.EmailLogModel;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req.CreateEmailLogRequest;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.res.EmailLogResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailLogRestMapper {
    EmailLogModel toModel(CreateEmailLogRequest request);
    EmailLogResponse toResponse(EmailLogModel model);
}
