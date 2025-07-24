package com.shopcloud.notification_mservice.infra.adapters.in.rest.controller;

import com.shopcloud.notification_mservice.application.ports.in.IEmailTemplateUseCases;
import com.shopcloud.notification_mservice.domain.models.EmailTemplateModel;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req.CreateEmailTemplate;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.req.UpdateEmailTemplate;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.dto.res.EmailTemplateResponse;
import com.shopcloud.notification_mservice.infra.adapters.in.rest.mapper.EmailTemplateRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/email-templates")
@RequiredArgsConstructor
@Tag(name = "Email Templates", description = "CRUD for email templates")
public class EmailTemplateController {

    private final IEmailTemplateUseCases iEmailTemplateUseCases;
    private final EmailTemplateRestMapper emailTemplateRestMapper;

    @PostMapping
    @Operation(summary = "Create a new template")
    @ApiResponse(responseCode = "201", description = "Template created successfully")
    public ResponseEntity<EmailTemplateResponse> create(@Valid @RequestBody CreateEmailTemplate request) {
        EmailTemplateModel model = emailTemplateRestMapper.toModel(request);
        EmailTemplateResponse response = emailTemplateRestMapper.toResponse(iEmailTemplateUseCases.createTemplate(model));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a template")
    @ApiResponse(responseCode = "200", description = "Template updated successfully")
    public ResponseEntity<EmailTemplateResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateEmailTemplate request
    ) {
        EmailTemplateModel existing = iEmailTemplateUseCases.getTemplateById(id);
        emailTemplateRestMapper.updateModelFromRequest(request, existing);
        EmailTemplateModel updated = iEmailTemplateUseCases.updateTemplate(id, existing);
        EmailTemplateResponse response = emailTemplateRestMapper.toResponse(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a template by ID")
    @ApiResponse(responseCode = "204", description = "Template deleted")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        iEmailTemplateUseCases.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get template by ID")
    @ApiResponse(responseCode = "200", description = "Template found")
    public ResponseEntity<EmailTemplateResponse> getById(@PathVariable UUID id) {
        EmailTemplateResponse response = emailTemplateRestMapper.toResponse(iEmailTemplateUseCases.getTemplateById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-code/{code}")
    @Operation(summary = "Get template by code")
    @ApiResponse(responseCode = "200", description = "Template found")
    public ResponseEntity<EmailTemplateResponse> getByCode(@PathVariable String code) {
        EmailTemplateResponse response = emailTemplateRestMapper.toResponse(iEmailTemplateUseCases.getTemplateByCode(code));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "List all templates")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    public ResponseEntity<List<EmailTemplateResponse>> getAll() {
        List<EmailTemplateResponse> responses = iEmailTemplateUseCases.getAllTemplates()
                .stream()
                .map(emailTemplateRestMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }
}