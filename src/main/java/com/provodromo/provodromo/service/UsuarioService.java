package com.provodromo.provodromo.service;

import com.provodromo.provodromo.model.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

import com.provodromo.provodromo.repository.UsuarioRepository;
import com.provodromo.provodromo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UsuarioService implements BaseService<Usuario> {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario findById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Set<Usuario> findAll() {
        return Set.copyOf(repository.findAll());
    }

    @Override
    public Usuario save(Usuario entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
