package com.medplatform.chantiermanagement.controller;

import com.medplatform.chantiermanagement.dto.TacheRequest;
import com.medplatform.chantiermanagement.dto.TacheResponse;
import com.medplatform.chantiermanagement.service.TacheService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
@RequiredArgsConstructor
public class TacheController {

    private final TacheService tacheService;

    @PostMapping
    public ResponseEntity<TacheResponse> create(@Valid @RequestBody TacheRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tacheService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<TacheResponse>> getAll() {
        return ResponseEntity.ok(tacheService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacheResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tacheService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TacheResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TacheRequest request) {
        return ResponseEntity.ok(tacheService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tacheService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
