package com.aston.bank_processing.service.abstracts;

import com.aston.bank_processing.models.Beneficial;

public interface BeneficialService extends ReadWriteService<Beneficial, Long> {
    Beneficial getBeneficialByName(String name);
}
