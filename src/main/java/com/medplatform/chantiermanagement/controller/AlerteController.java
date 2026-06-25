package com.medplatform.chantiermanagement.controller;

import com.medplatform.chantiermanagement.dto.AlerteRequest;
import com.medplatform.chantiermanagement.dto.AlerteResponse;
import com.medplatform.chantiermanagement.service.AlerteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertes")
@RequiredArgsConstructor
public class AlerteController {

    private final AlerteService alerteService;

    @PostMapping
    public ResponseEntity<AlerteResponse> create(@Valid @RequestBody AlerteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alerteService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<AlerteResponse>> getAll() {
        return ResponseEntity.ok(alerteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlerteResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(alerteService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlerteResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AlerteRequest request) {
        return ResponseEntity.ok(alerteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alerteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
