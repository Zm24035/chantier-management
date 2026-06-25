package com.medplatform.chantiermanagement.service;

import com.medplatform.chantiermanagement.dto.TacheRequest;
import com.medplatform.chantiermanagement.dto.TacheResponse;
import com.medplatform.chantiermanagement.entity.Chantier;
import com.medplatform.chantiermanagement.entity.Tache;
import com.medplatform.chantiermanagement.exception.ChantierNotFoundException;
import com.medplatform.chantiermanagement.exception.TacheNotFoundException;
import com.medplatform.chantiermanagement.repository.ChantierRepository;
import com.medplatform.chantiermanagement.repository.TacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TacheService {

    private final TacheRepository tacheRepository;
    private final ChantierRepository chantierRepository;

    public TacheResponse create(TacheRequest request) {
        Chantier chantier = resolveChantier(request.getChantierId());

        Tache tache = Tache.builder()
                .titre(request.getTitre())
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .statut(request.getStatut())
                .chantier(chantier)
                .build();

        return toResponse(tacheRepository.save(tache));
    }

    public List<TacheResponse> getAll() {
        return tacheRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TacheResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public TacheResponse update(Long id, TacheRequest request) {
        Tache tache = findOrThrow(id);
        Chantier chantier = resolveChantier(request.getChantierId());

        tache.setTitre(request.getTitre());
        tache.setDescription(request.getDescription());
        tache.setDateDebut(request.getDateDebut());
        tache.setDateFin(request.getDateFin());
        tache.setStatut(request.getStatut());
        tache.setChantier(chantier);

        return toResponse(tacheRepository.save(tache));
    }

    public void delete(Long id) {
        findOrThrow(id);
        tacheRepository.deleteById(id);
    }

    private Tache findOrThrow(Long id) {
        return tacheRepository.findById(id)
                .orElseThrow(() -> new TacheNotFoundException(id));
    }

    private Chantier resolveChantier(Long chantierId) {
        return chantierRepository.findById(chantierId)
                .orElseThrow(() -> new ChantierNotFoundException(chantierId));
    }

    private TacheResponse toResponse(Tache tache) {
        return TacheResponse.builder()
                .id(tache.getId())
                .titre(tache.getTitre())
                .description(tache.getDescription())
                .dateDebut(tache.getDateDebut())
                .dateFin(tache.getDateFin())
                .statut(tache.getStatut())
                .chantierId(tache.getChantier() != null ? tache.getChantier().getId() : null)
                .build();
    }
}
