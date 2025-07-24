package com.shopcloud.notification_mservice.infra.adapters.in.rabbit.mapper;

import com.shopcloud.notification_mservice.domain.models.EmailMessageModel;
import com.shopcloud.notification_mservice.infra.adapters.in.rabbit.dto.EmailMessageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMessageMapper {
    EmailMessageModel toModel(EmailMessageDTO dto);
}

