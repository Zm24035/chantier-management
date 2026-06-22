package com.medplatform.chantiermanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "chantier")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chantier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "localisation", nullable = false)
    private String localisation;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin_prevue")
    private LocalDate dateFinPrevue;

    @Column(name = "budget", precision = 15, scale = 2)
    private BigDecimal budget;

    @Column(name = "statut", length = 50)
    private String statut;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chef_id")
    private Utilisateur chef;
}
