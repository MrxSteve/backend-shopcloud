package com.shopcloud.notification_mservice.infra.adapters.in.rest.controller;

import com.shopcloud.notification_mservice.application.ports.in.IEmailLogUseCases;
import com.shopcloud.notification_mservice.domain.models.EmailLogModel;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req.CreateEmailLogRequest;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.res.EmailLogResponse;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.mapper.EmailLogRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/email-logs")
@RequiredArgsConstructor
@Tag(name = "Email Logs", description = "Endpoints to query and register email logs")
public class EmailLogController {
    private final IEmailLogUseCases iEmailLogUseCases;
    private final EmailLogRestMapper emailLogRestMapper;

    @PostMapping
    @Operation(summary = "Create an email log", description = "Manually register an email sending log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Log created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    public ResponseEntity<EmailLogResponse> create(@Valid @RequestBody CreateEmailLogRequest request) {
        EmailLogModel model = emailLogRestMapper.toModel(request);
        EmailLogModel saved = iEmailLogUseCases.createLog(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(emailLogRestMapper.toResponse(saved));
    }

    @GetMapping
    @Operation(summary = "List email logs", description = "Get all logs paginated")
    public ResponseEntity<List<EmailLogResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<EmailLogModel> logs = iEmailLogUseCases.getAllLogs(page, size);
        List<EmailLogResponse> responses = logs.stream()
                .map(emailLogRestMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get log by ID")
    public ResponseEntity<EmailLogResponse> getById(@PathVariable UUID id) {
        EmailLogModel model = iEmailLogUseCases.getLogById(id);
        return ResponseEntity.ok(emailLogRestMapper.toResponse(model));
    }

    @GetMapping("/by-status")
    @Operation(summary = "Filter logs by status (SENT or FAILED)")
    public ResponseEntity<List<EmailLogResponse>> getByStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<EmailLogModel> logs = iEmailLogUseCases.getLogsByStatus(status, page, size);
        List<EmailLogResponse> responses = logs.stream()
                .map(emailLogRestMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/by-email")
    @Operation(summary = "Filter logs by recipient")
    public ResponseEntity<List<EmailLogResponse>> getByEmail(
            @RequestParam String toEmail,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<EmailLogModel> logs = iEmailLogUseCases.getLogsByEmail(toEmail, page, size);
        List<EmailLogResponse> responses = logs.stream()
                .map(emailLogRestMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }
}

