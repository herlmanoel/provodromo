package com.provodromo.provodromo.service;

import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.model.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

import com.provodromo.provodromo.repository.TurmaRepository;
import com.provodromo.provodromo.repository.UsuarioRepository;
import com.provodromo.provodromo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UsuarioService implements BaseService<Usuario> {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TurmaRepository turmaRepository;

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
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));

        turmaRepository.deleteByProfessor(usuario.getId());

        usuario.setTipoUsuario(null);

        turmaRepository.flush();
        repository.save(usuario);
        repository.deleteById(id);
    }

    public Set<Usuario> findByTipoUsuario(Long id) {
        return repository.findAllByTipoUsuarioId(id);
    };
}
