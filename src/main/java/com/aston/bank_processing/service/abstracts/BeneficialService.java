package com.aston.bank_processing.service.abstracts;

import com.aston.bank_processing.models.Beneficial;

import java.util.Optional;

public interface BeneficialService extends ReadWriteService<Beneficial, Long> {
    Optional<Beneficial> getBeneficialByName(String name);
}
