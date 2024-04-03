package com.provodromo.provodromo.service;

import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.repository.TipoUsuarioRepository;
import com.provodromo.provodromo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TipoUsuarioService implements BaseService<TipoUsuario> {

    @Autowired
    private TipoUsuarioRepository repository;

    @Override
    public TipoUsuario findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Set<TipoUsuario> findAll() {
        Set<TipoUsuario> tipoUsuarios = new HashSet<>();
        repository.findAll().forEach(tipoUsuarios::add);
        return tipoUsuarios;
    }

    @Override
    public TipoUsuario save(TipoUsuario entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
