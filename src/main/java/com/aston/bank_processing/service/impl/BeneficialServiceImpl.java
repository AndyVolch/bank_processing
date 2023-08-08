package com.aston.bank_processing.service.impl;

import com.aston.bank_processing.dao.BeneficialDao;
import com.aston.bank_processing.models.Beneficial;
import com.aston.bank_processing.service.abstracts.BeneficialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BeneficialServiceImpl implements BeneficialService {
    private final BeneficialDao beneficialDao;

    public BeneficialServiceImpl(BeneficialDao beneficialDao) {
        this.beneficialDao = beneficialDao;
    }

    @Override
    public List<Beneficial> getAll() {
        return beneficialDao.findAll();
    }

    @Override
    public Optional<Beneficial> getById(Long id) {
        return beneficialDao.findById(id);
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
    public Optional<Beneficial> getBeneficialByName(String name) {
        return beneficialDao.getBeneficialByName(name);
    }
}
