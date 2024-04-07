package com.provodromo.provodromo.service;

import com.provodromo.provodromo.model.Turma;
import com.provodromo.provodromo.repository.TurmaRepository;
import com.provodromo.provodromo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TurmaService implements BaseService<Turma> {

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public Turma findById(Long id) {
        return turmaRepository.getReferenceById(id);
    }

    @Override
    public Set<Turma> findAll() {
        return Set.copyOf(turmaRepository.findAll());
    }

    @Override
    public Turma save(Turma entity) {
        return turmaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        turmaRepository.deleteById(id);
    }
}
