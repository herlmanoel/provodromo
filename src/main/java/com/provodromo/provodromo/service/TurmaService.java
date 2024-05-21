package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.TurmaDTO;
import com.provodromo.provodromo.model.Turma;
import com.provodromo.provodromo.repository.TurmaRepository;
import com.provodromo.provodromo.repository.UsuarioRepository;
import com.provodromo.provodromo.service.base.BaseServiceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TurmaService implements BaseServiceNew<TurmaDTO, Long> {
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public TurmaDTO findById(Long id) {
        Turma turma = turmaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Turma não encontrada com o ID: " + id)
        );

        return entityToDTO(turma);
    }

    @Override
    public Set<TurmaDTO> findAll() {
        return turmaRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toSet());
    }

    @Override
    public TurmaDTO update(Long id, TurmaDTO turmaDTO) {
        if (turmaDTO == null || turmaDTO.getNome() == null || turmaDTO.getProfessorId() == null) {
            throw new IllegalArgumentException("Dados da Turma inválidos");
        }

        if (!turmaRepository.existsById(id)) {
            throw new IllegalArgumentException("Turma não encontrada com o ID: " + id);
        }

        return entityToDTO(turmaRepository.save(dtoToEntity(turmaDTO)));
    }

    @Override
    public TurmaDTO create(TurmaDTO turmaDTO) {
        if (turmaDTO == null || turmaDTO.getNome() == null || turmaDTO.getProfessorId() == null) {
            throw new IllegalArgumentException("Dados da Turma inválidos");
        }

        return entityToDTO(turmaRepository.save(dtoToEntity(turmaDTO)));
    }

    @Override
    public void deleteById(Long aLong) {
        if (!turmaRepository.existsById(aLong)) {
            throw new IllegalArgumentException("Turma não encontrada com o ID: " + aLong);
        }

        turmaRepository.deleteById(aLong);
    }

    private TurmaDTO entityToDTO(Turma turma) {
        if (turma == null) {
            return null;
        }

        return new TurmaDTO(
                turma.getNome(),
                turma.getProfessor().getId()
        );
    }

    private Turma dtoToEntity(TurmaDTO turmaDTO) {
        if (turmaDTO == null) {
            return null;
        }

        Turma turma = new Turma();
        turma.setNome(turmaDTO.getNome());
        turma.setProfessor(usuarioRepository.findById(turmaDTO.getProfessorId()).orElseThrow(
                () -> new IllegalArgumentException("Professor não encontrado com o ID: " + turmaDTO.getProfessorId())
        ));

        return turma;
    }
}
