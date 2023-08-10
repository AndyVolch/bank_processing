package com.aston.bank_processing.service.abstracts;

import java.util.List;

public interface ReadOnlyService<E, K> {
    List<E> getAll();
    E getById(K id);
}
