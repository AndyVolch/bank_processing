package com.aston.bank_processing.dao;

import com.aston.bank_processing.models.Beneficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficialDao extends JpaRepository<Beneficial, Long> {
    Optional<Beneficial> getBeneficialByName(String name);
}
