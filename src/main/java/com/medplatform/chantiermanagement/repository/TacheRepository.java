package com.medplatform.chantiermanagement.repository;

import com.medplatform.chantiermanagement.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {
}
