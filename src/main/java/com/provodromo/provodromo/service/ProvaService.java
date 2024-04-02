package com.provodromo.provodromo.service;

import com.provodromo.provodromo.model.Prova;
import com.provodromo.provodromo.repository.ProvaRepository;
import com.provodromo.provodromo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProvaService implements BaseService<Prova> {

    @Autowired
    private ProvaRepository provaRepository;


    @Override
    public Prova findById(Long id) {
        return provaRepository.getReferenceById(id);
    }

    @Override
    public Set<Prova> findAll() {
        return Set.copyOf(provaRepository.findAll());
    }

    @Override
    public Prova save(Prova entity) {
        return provaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        provaRepository.deleteById(id);
    }
}
