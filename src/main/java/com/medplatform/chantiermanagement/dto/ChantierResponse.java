package com.medplatform.chantiermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChantierResponse {

    private Long id;
    private String nom;
    private String localisation;
    private LocalDate dateDebut;
    private LocalDate dateFinPrevue;
    private BigDecimal budget;
    private String statut;
    private Long chefId;
    private String chefNom;
}
