package com.medplatform.chantiermanagement.repository;

import com.medplatform.chantiermanagement.entity.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {
}
