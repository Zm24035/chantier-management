package com.medplatform.chantiermanagement.repository;

import com.medplatform.chantiermanagement.entity.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChantierRepository extends JpaRepository<Chantier, Long> {
}
