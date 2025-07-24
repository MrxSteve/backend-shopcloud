package com.shopcloud.notification_mservice.infra.adapters.in.rest.controller;

import com.shopcloud.notification_mservice.infra.adapters.in.rabbit.dto.EmailMessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test-email")
@RequiredArgsConstructor
@Tag(name = "Test Email Publisher", description = "Endpoints to send test emails to RabbitMQ")
public class TestEmailPublisherController {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing-key.email}")
    private String routingKey;

    @Operation(
            summary = "Send a test email to RabbitMQ",
            description = "Publishes a test email message to the configured RabbitMQ exchange and routing key. The request body must be a valid EmailMessageDTO."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message sent successfully to RabbitMQ"),
            @ApiResponse(responseCode = "400", description = "Invalid request (incorrect EmailMessageDTO format)"),
            @ApiResponse(responseCode = "500", description = "Internal error while publishing the message")
    })
    @PostMapping("/send")
    public ResponseEntity<String> sendTestEmail(
            @Parameter(description = "DTO containing the email data to send", required = true)
            @RequestBody EmailMessageDTO message
    ) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return ResponseEntity.ok("Message sent to RabbitMQ successfully");
    }
}