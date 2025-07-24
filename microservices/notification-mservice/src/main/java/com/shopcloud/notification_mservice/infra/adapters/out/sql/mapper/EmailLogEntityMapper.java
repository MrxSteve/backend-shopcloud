package com.shopcloud.notification_mservice.infra.adapters.out.sql.mapper;

import com.shopcloud.notification_mservice.domain.models.EmailLogModel;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.entity.EmailLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailLogEntityMapper {
    @Mapping(target = "template", ignore = true)
    EmailLogEntity toEntity(EmailLogModel model);
    @Mapping(source = "template.code", target = "templateCode")
    EmailLogModel toModel(EmailLogEntity entity);

    List<EmailLogModel> toModelList(List<EmailLogEntity> entities);
}

