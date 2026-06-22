package com.medplatform.chantiermanagement.service;

import com.medplatform.chantiermanagement.dto.ChantierRequest;
import com.medplatform.chantiermanagement.dto.ChantierResponse;
import com.medplatform.chantiermanagement.entity.Chantier;
import com.medplatform.chantiermanagement.entity.Utilisateur;
import com.medplatform.chantiermanagement.exception.ChantierNotFoundException;
import com.medplatform.chantiermanagement.repository.ChantierRepository;
import com.medplatform.chantiermanagement.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChantierService {

    private final ChantierRepository chantierRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ChantierResponse create(ChantierRequest request) {
        Utilisateur chef = resolveChef(request.getChefId());

        Chantier chantier = Chantier.builder()
                .nom(request.getNom())
                .localisation(request.getLocalisation())
                .dateDebut(request.getDateDebut())
                .dateFinPrevue(request.getDateFinPrevue())
                .budget(request.getBudget())
                .statut(request.getStatut())
                .chef(chef)
                .build();

        return toResponse(chantierRepository.save(chantier));
    }

    public List<ChantierResponse> getAll() {
        return chantierRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ChantierResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public ChantierResponse update(Long id, ChantierRequest request) {
        Chantier chantier = findOrThrow(id);
        Utilisateur chef = resolveChef(request.getChefId());

        chantier.setNom(request.getNom());
        chantier.setLocalisation(request.getLocalisation());
        chantier.setDateDebut(request.getDateDebut());
        chantier.setDateFinPrevue(request.getDateFinPrevue());
        chantier.setBudget(request.getBudget());
        chantier.setStatut(request.getStatut());
        chantier.setChef(chef);

        return toResponse(chantierRepository.save(chantier));
    }

    public void delete(Long id) {
        findOrThrow(id);
        chantierRepository.deleteById(id);
    }

    private Chantier findOrThrow(Long id) {
        return chantierRepository.findById(id)
                .orElseThrow(() -> new ChantierNotFoundException(id));
    }

    private Utilisateur resolveChef(Long chefId) {
        if (chefId == null) return null;
        return utilisateurRepository.findById(chefId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + chefId));
    }

    private ChantierResponse toResponse(Chantier chantier) {
        return ChantierResponse.builder()
                .id(chantier.getId())
                .nom(chantier.getNom())
                .localisation(chantier.getLocalisation())
                .dateDebut(chantier.getDateDebut())
                .dateFinPrevue(chantier.getDateFinPrevue())
                .budget(chantier.getBudget())
                .statut(chantier.getStatut())
                .chefId(chantier.getChef() != null ? chantier.getChef().getId() : null)
                .chefNom(chantier.getChef() != null ? chantier.getChef().getNom() : null)
                .build();
    }
}
