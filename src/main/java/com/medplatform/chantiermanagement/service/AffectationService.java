package com.medplatform.chantiermanagement.service;

import com.medplatform.chantiermanagement.dto.AffectationRequest;
import com.medplatform.chantiermanagement.dto.AffectationResponse;
import com.medplatform.chantiermanagement.entity.Affectation;
import com.medplatform.chantiermanagement.entity.Chantier;
import com.medplatform.chantiermanagement.entity.Utilisateur;
import com.medplatform.chantiermanagement.exception.AffectationNotFoundException;
import com.medplatform.chantiermanagement.exception.ChantierNotFoundException;
import com.medplatform.chantiermanagement.exception.UtilisateurNotFoundException;
import com.medplatform.chantiermanagement.repository.AffectationRepository;
import com.medplatform.chantiermanagement.repository.ChantierRepository;
import com.medplatform.chantiermanagement.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AffectationService {

    private final AffectationRepository affectationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ChantierRepository chantierRepository;

    public AffectationResponse create(AffectationRequest request) {
        Utilisateur utilisateur = resolveUtilisateur(request.getUtilisateurId());
        Chantier chantier = resolveChantier(request.getChantierId());

        Affectation affectation = Affectation.builder()
                .utilisateur(utilisateur)
                .chantier(chantier)
                .dateAffectation(request.getDateAffectation())
                .build();

        return toResponse(affectationRepository.save(affectation));
    }

    public List<AffectationResponse> getAll() {
        return affectationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AffectationResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public AffectationResponse update(Long id, AffectationRequest request) {
        Affectation affectation = findOrThrow(id);
        Utilisateur utilisateur = resolveUtilisateur(request.getUtilisateurId());
        Chantier chantier = resolveChantier(request.getChantierId());

        affectation.setUtilisateur(utilisateur);
        affectation.setChantier(chantier);
        affectation.setDateAffectation(request.getDateAffectation());

        return toResponse(affectationRepository.save(affectation));
    }

    public void delete(Long id) {
        findOrThrow(id);
        affectationRepository.deleteById(id);
    }

    private Affectation findOrThrow(Long id) {
        return affectationRepository.findById(id)
                .orElseThrow(() -> new AffectationNotFoundException(id));
    }

    private Utilisateur resolveUtilisateur(Long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new UtilisateurNotFoundException(utilisateurId));
    }

    private Chantier resolveChantier(Long chantierId) {
        return chantierRepository.findById(chantierId)
                .orElseThrow(() -> new ChantierNotFoundException(chantierId));
    }

    private AffectationResponse toResponse(Affectation affectation) {
        return AffectationResponse.builder()
                .id(affectation.getId())
                .utilisateurId(affectation.getUtilisateur() != null ? affectation.getUtilisateur().getId() : null)
                .chantierId(affectation.getChantier() != null ? affectation.getChantier().getId() : null)
                .dateAffectation(affectation.getDateAffectation())
                .build();
    }
}
