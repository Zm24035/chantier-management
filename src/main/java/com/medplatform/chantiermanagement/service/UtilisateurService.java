package com.medplatform.chantiermanagement.service;

import com.medplatform.chantiermanagement.dto.UtilisateurRequest;
import com.medplatform.chantiermanagement.dto.UtilisateurResponse;
import com.medplatform.chantiermanagement.entity.Utilisateur;
import com.medplatform.chantiermanagement.exception.UtilisateurNotFoundException;
import com.medplatform.chantiermanagement.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurResponse create(UtilisateurRequest request) {
        Utilisateur utilisateur = Utilisateur.builder()
                .nom(request.getNom())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .role(request.getRole())
                .motDePasse(passwordEncoder.encode(request.getMotDePasse()))
                .build();

        return toResponse(utilisateurRepository.save(utilisateur));
    }

    public List<UtilisateurResponse> getAll() {
        return utilisateurRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UtilisateurResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public UtilisateurResponse update(Long id, UtilisateurRequest request) {
        Utilisateur utilisateur = findOrThrow(id);

        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setTelephone(request.getTelephone());
        utilisateur.setRole(request.getRole());

        return toResponse(utilisateurRepository.save(utilisateur));
    }

    public void delete(Long id) {
        findOrThrow(id);
        utilisateurRepository.deleteById(id);
    }

    private Utilisateur findOrThrow(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new UtilisateurNotFoundException(id));
    }

    private UtilisateurResponse toResponse(Utilisateur utilisateur) {
        return UtilisateurResponse.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .email(utilisateur.getEmail())
                .telephone(utilisateur.getTelephone())
                .role(utilisateur.getRole())
                .build();
    }
}
