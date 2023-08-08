package com.aston.bank_processing.service.abstracts;

import java.util.List;
import java.util.Optional;

public interface ReadOnlyService<E, K> {
    List<E> getAll();
    Optional<E> getById(K id);
}
