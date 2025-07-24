package com.shopcloud.notification_mservice.infra.adapters.out.sql.mapper;

import com.shopcloud.notification_mservice.domain.models.EmailTemplateModel;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.entity.EmailTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailTemplateEntityMapper {
    EmailTemplateModel toModel(EmailTemplateEntity entity);
    EmailTemplateEntity toEntity(EmailTemplateModel model);
}
