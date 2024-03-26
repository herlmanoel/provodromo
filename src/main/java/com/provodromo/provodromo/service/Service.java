package com.provodromo.provodromo.service;

import java.util.Set;

public interface Service <T> {
    T findById(Long id);
    Set<T> findAll();
    T save(T entity);
    void deleteById(Long id);
}
