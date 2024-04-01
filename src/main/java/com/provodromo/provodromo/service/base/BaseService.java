package com.provodromo.provodromo.service.base;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BaseService <T> {
    T findById(Long id);
    Set<T> findAll();
    T save(T entity);
    void deleteById(Long id);
}
