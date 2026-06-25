package com.medplatform.chantiermanagement.controller;

import com.medplatform.chantiermanagement.dto.PhotoRequest;
import com.medplatform.chantiermanagement.dto.PhotoResponse;
import com.medplatform.chantiermanagement.service.PhotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping
    public ResponseEntity<PhotoResponse> create(@Valid @RequestBody PhotoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(photoService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PhotoResponse>> getAll() {
        return ResponseEntity.ok(photoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(photoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PhotoRequest request) {
        return ResponseEntity.ok(photoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        photoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
