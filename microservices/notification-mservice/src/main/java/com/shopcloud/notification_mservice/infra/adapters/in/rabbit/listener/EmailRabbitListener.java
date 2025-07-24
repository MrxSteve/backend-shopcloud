package com.shopcloud.notification_mservice.infra.adapters.in.rabbit.listener;

import com.shopcloud.notification_mservice.application.ports.in.EmailMessageListener;
import com.shopcloud.notification_mservice.domain.models.EmailMessageModel;
import com.shopcloud.notification_mservice.infra.adapters.in.rabbit.dto.EmailMessageDTO;
import com.shopcloud.notification_mservice.infra.adapters.in.rabbit.mapper.EmailMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailRabbitListener {

    private final EmailMessageMapper emailMessageMapper;
    private final EmailMessageListener emailMessageListener;

    @RabbitListener(queues = "${rabbitmq.queue.email}")
    public void receive(EmailMessageDTO messageDTO) {
        EmailMessageModel message = emailMessageMapper.toModel(messageDTO);
        emailMessageListener.receiveEmailMessage(message);
    }
}


