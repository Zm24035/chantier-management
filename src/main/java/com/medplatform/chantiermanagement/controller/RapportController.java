package com.medplatform.chantiermanagement.controller;

import com.medplatform.chantiermanagement.dto.RapportRequest;
import com.medplatform.chantiermanagement.dto.RapportResponse;
import com.medplatform.chantiermanagement.service.RapportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rapports")
@RequiredArgsConstructor
public class RapportController {

    private final RapportService rapportService;

    @PostMapping
    public ResponseEntity<RapportResponse> create(@Valid @RequestBody RapportRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rapportService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<RapportResponse>> getAll() {
        return ResponseEntity.ok(rapportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RapportResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rapportService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RapportResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody RapportRequest request) {
        return ResponseEntity.ok(rapportService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rapportService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
