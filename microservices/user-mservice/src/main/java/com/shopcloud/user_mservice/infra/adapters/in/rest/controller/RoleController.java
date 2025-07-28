package com.shopcloud.user_mservice.infra.adapters.in.rest.controller;

import com.shopcloud.user_mservice.application.ports.in.IRoleUseCases;
import com.shopcloud.user_mservice.domain.models.RoleModel;
import com.shopcloud.user_mservice.infra.adapters.in.rest.dto.req.RoleRequest;
import com.shopcloud.user_mservice.infra.adapters.in.rest.dto.res.RoleResponse;
import com.shopcloud.user_mservice.infra.adapters.in.rest.mapper.RoleRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Tag(name = "Roles", description = "CRUD operations for role management")
public class RoleController {
    private final IRoleUseCases iRoleUseCases;
    private final RoleRestMapper roleRestMapper;

    @Operation(summary = "Create a new role")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody RoleRequest request) {
        RoleModel model = roleRestMapper.toModel(request);
        RoleModel saved = iRoleUseCases.createRole(model);
        return ResponseEntity.status(201).body(roleRestMapper.toResponse(saved));
    }

    @Operation(summary = "Get role by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role found"),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getById(@PathVariable UUID id) {
        RoleModel found = iRoleUseCases.getRoleById(id);
        return ResponseEntity.ok(roleRestMapper.toResponse(found));
    }

    @Operation(summary = "Get role by name")
    @GetMapping("/by-name/{name}")
    public ResponseEntity<RoleResponse> getByName(@PathVariable String name) {
        RoleModel found = iRoleUseCases.getRoleByName(name);
        return ResponseEntity.ok(roleRestMapper.toResponse(found));
    }

    @Operation(summary = "List all roles (paginated)")
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAll(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        List<RoleModel> roles = iRoleUseCases.getAllRoles(page, size);
        return ResponseEntity.ok(roles.stream()
                .map(roleRestMapper::toResponse)
                .toList());
    }

    @Operation(summary = "Delete role by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        iRoleUseCases.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }
}
