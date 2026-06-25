package com.medplatform.chantiermanagement.controller;

import com.medplatform.chantiermanagement.dto.DepenseRequest;
import com.medplatform.chantiermanagement.dto.DepenseResponse;
import com.medplatform.chantiermanagement.service.DepenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depenses")
@RequiredArgsConstructor
public class DepenseController {

    private final DepenseService depenseService;

    @PostMapping
    public ResponseEntity<DepenseResponse> create(@Valid @RequestBody DepenseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(depenseService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<DepenseResponse>> getAll() {
        return ResponseEntity.ok(depenseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepenseResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(depenseService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepenseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody DepenseRequest request) {
        return ResponseEntity.ok(depenseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        depenseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
