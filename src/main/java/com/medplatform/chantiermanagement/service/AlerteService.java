package com.medplatform.chantiermanagement.service;

import com.medplatform.chantiermanagement.dto.AlerteRequest;
import com.medplatform.chantiermanagement.dto.AlerteResponse;
import com.medplatform.chantiermanagement.entity.Alerte;
import com.medplatform.chantiermanagement.entity.Chantier;
import com.medplatform.chantiermanagement.exception.AlerteNotFoundException;
import com.medplatform.chantiermanagement.exception.ChantierNotFoundException;
import com.medplatform.chantiermanagement.repository.AlerteRepository;
import com.medplatform.chantiermanagement.repository.ChantierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlerteService {

    private final AlerteRepository alerteRepository;
    private final ChantierRepository chantierRepository;

    public AlerteResponse create(AlerteRequest request) {
        Chantier chantier = resolveChantier(request.getChantierId());

        Alerte alerte = Alerte.builder()
                .message(request.getMessage())
                .dateCreation(request.getDateCreation())
                .statut(request.getStatut())
                .chantier(chantier)
                .build();

        return toResponse(alerteRepository.save(alerte));
    }

    public List<AlerteResponse> getAll() {
        return alerteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlerteResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public AlerteResponse update(Long id, AlerteRequest request) {
        Alerte alerte = findOrThrow(id);
        Chantier chantier = resolveChantier(request.getChantierId());

        alerte.setMessage(request.getMessage());
        alerte.setDateCreation(request.getDateCreation());
        alerte.setStatut(request.getStatut());
        alerte.setChantier(chantier);

        return toResponse(alerteRepository.save(alerte));
    }

    public void delete(Long id) {
        findOrThrow(id);
        alerteRepository.deleteById(id);
    }

    private Alerte findOrThrow(Long id) {
        return alerteRepository.findById(id)
                .orElseThrow(() -> new AlerteNotFoundException(id));
    }

    private Chantier resolveChantier(Long chantierId) {
        return chantierRepository.findById(chantierId)
                .orElseThrow(() -> new ChantierNotFoundException(chantierId));
    }

    private AlerteResponse toResponse(Alerte alerte) {
        return AlerteResponse.builder()
                .id(alerte.getId())
                .message(alerte.getMessage())
                .dateCreation(alerte.getDateCreation())
                .statut(alerte.getStatut())
                .chantierId(alerte.getChantier() != null ? alerte.getChantier().getId() : null)
                .build();
    }
}
