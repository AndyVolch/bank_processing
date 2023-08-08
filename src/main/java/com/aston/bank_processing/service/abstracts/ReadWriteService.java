package com.aston.bank_processing.service.abstracts;

public interface ReadWriteService<E, K> extends ReadOnlyService<E, K> {
    void save(E e);

    void deleteById(K id);
}
