package com.medplatform.chantiermanagement.controller;

import com.medplatform.chantiermanagement.dto.AffectationRequest;
import com.medplatform.chantiermanagement.dto.AffectationResponse;
import com.medplatform.chantiermanagement.service.AffectationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affectations")
@RequiredArgsConstructor
public class AffectationController {

    private final AffectationService affectationService;

    @PostMapping
    public ResponseEntity<AffectationResponse> create(@Valid @RequestBody AffectationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(affectationService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<AffectationResponse>> getAll() {
        return ResponseEntity.ok(affectationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AffectationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(affectationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AffectationResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody AffectationRequest request) {
        return ResponseEntity.ok(affectationService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        affectationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
