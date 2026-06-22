package com.medplatform.chantiermanagement.controller;

import com.medplatform.chantiermanagement.dto.ChantierRequest;
import com.medplatform.chantiermanagement.dto.ChantierResponse;
import com.medplatform.chantiermanagement.service.ChantierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chantiers")
@RequiredArgsConstructor
public class ChantierController {

    private final ChantierService chantierService;

    @PostMapping
    public ResponseEntity<ChantierResponse> create(@Valid @RequestBody ChantierRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chantierService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ChantierResponse>> getAll() {
        return ResponseEntity.ok(chantierService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChantierResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(chantierService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChantierResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ChantierRequest request) {
        return ResponseEntity.ok(chantierService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chantierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
