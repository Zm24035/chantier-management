package com.medplatform.chantiermanagement.controller;

import com.medplatform.chantiermanagement.dto.UtilisateurRequest;
import com.medplatform.chantiermanagement.dto.UtilisateurResponse;
import com.medplatform.chantiermanagement.service.UtilisateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<UtilisateurResponse> create(@Valid @RequestBody UtilisateurRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurResponse>> getAll() {
        return ResponseEntity.ok(utilisateurService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UtilisateurRequest request) {
        return ResponseEntity.ok(utilisateurService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
