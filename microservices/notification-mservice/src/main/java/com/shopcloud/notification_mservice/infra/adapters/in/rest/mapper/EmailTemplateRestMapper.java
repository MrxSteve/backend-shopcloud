package com.shopcloud.notification_mservice.infra.adapters.in.rest.mapper;

import com.shopcloud.notification_mservice.domain.models.EmailTemplateModel;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req.CreateEmailTemplate;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req.UpdateEmailTemplate;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.res.EmailTemplateResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EmailTemplateRestMapper {

    EmailTemplateModel toModel(CreateEmailTemplate request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromRequest(UpdateEmailTemplate request, @MappingTarget EmailTemplateModel model);

    EmailTemplateResponse toResponse(EmailTemplateModel model);

    // List<EmailTemplateResponse> toResponseList(List<EmailTemplateModel> models);
}

