package com.aston.bank_processing.service.impl;

import com.aston.bank_processing.dao.BeneficialDao;
import com.aston.bank_processing.models.Beneficial;
import com.aston.bank_processing.service.abstracts.BeneficialService;
import com.aston.bank_processing.service.abstracts.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BeneficialServiceImpl implements BeneficialService {
    private final BeneficialDao beneficialDao;
    private final ValidationService<Beneficial> validationService;

    public BeneficialServiceImpl(BeneficialDao beneficialDao,
                                 ValidationService<Beneficial> validationService) {

        this.beneficialDao = beneficialDao;
        this.validationService = validationService;
    }

    @Override
    public List<Beneficial> getAll() {
        return beneficialDao.findAll();
    }

    @Override
    public Beneficial getById(Long id) {
        Optional<Beneficial> beneficial = beneficialDao.findById(id);
        validationService.checkIfEntityExists(beneficial);
        return beneficial.get();
    }

    @Override
    public void save(Beneficial beneficial) {
        beneficialDao.save(beneficial);
    }

    @Override
    public void deleteById(Long id) {
        beneficialDao.deleteById(id);
    }

    @Override
    public Beneficial getBeneficialByName(String name) {
        Optional<Beneficial> beneficial = beneficialDao.getBeneficialByName(name);
        validationService.checkIfEntityExists(beneficial);
        return beneficial.get();
    }
}
