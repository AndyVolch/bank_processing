package com.aston.bank_processing.service;

import com.aston.bank_processing.dao.BeneficialDao;
import com.aston.bank_processing.models.Beneficial;
import com.aston.bank_processing.service.abstracts.ValidationService;
import com.aston.bank_processing.service.impl.BeneficialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BeneficialServiceImplTest {
    @Mock
    private BeneficialDao beneficialDao;

    @Mock
    private ValidationService<Beneficial> validationService;

    @InjectMocks
    private BeneficialServiceImpl beneficialService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBeneficials() {
        when(beneficialDao.findAll()).thenReturn(List.of(new Beneficial(), new Beneficial()));

        List<Beneficial> beneficials = beneficialService.getAll();

        assertThat(beneficials).hasSize(2);
    }

    @Test
    public void testGetBeneficialById() {

        Beneficial beneficial = new Beneficial();
        beneficial.setId(1L);
        when(beneficialDao.findById(1L)).thenReturn(Optional.of(beneficial));

        Beneficial retrievedBeneficial = beneficialService.getById(1L);

        assertThat(retrievedBeneficial).isEqualTo(beneficial);
    }
    @Test
    public void testGetBeneficialByIdIfAbsent() {
        Long id = 1L;
        when(beneficialDao.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> beneficialService.getById(id));
    }
    @Test
    public void testGetBeneficialByName() {
        Beneficial beneficial = new Beneficial();
        String name = "John";
        beneficial.setName(name);
        when(beneficialDao.getBeneficialByName(name)).thenReturn(Optional.of(beneficial));

        Beneficial retrievedBeneficial = beneficialService.getBeneficialByName(name);

        assertThat(retrievedBeneficial).isEqualTo(beneficial);
    }

    @Test
    public void testGetBeneficialByNameIfAbsent() {
        String name = "John";
        when(beneficialDao.getBeneficialByName(name)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> beneficialService.getBeneficialByName(name));
    }
}
