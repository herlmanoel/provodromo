package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.request.TurmaRequestDTO;
import com.provodromo.provodromo.dto.response.TurmaResponseDTO;
import com.provodromo.provodromo.model.Turma;
import com.provodromo.provodromo.repository.TurmaRepository;
import com.provodromo.provodromo.repository.UsuarioRepository;
import com.provodromo.provodromo.service.base.BaseServiceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TurmaService implements BaseServiceNew<TurmaRequestDTO, TurmaResponseDTO, Long> {
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public TurmaResponseDTO findById(Long id) {
        Turma turma = turmaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Turma não encontrada com o ID: " + id)
        );

        return convertToTurmaResponseDTO(turma);
    }

    @Override
    public Set<TurmaResponseDTO> findAll() {
        return turmaRepository.findAll().stream().map(this::convertToTurmaResponseDTO).collect(Collectors.toSet());
    }

    @Override
    public TurmaResponseDTO update(Long id, TurmaRequestDTO turmaRequestDTO) {
        if (turmaRequestDTO == null || turmaRequestDTO.getNome() == null || turmaRequestDTO.getProfessorId() == null) {
            throw new IllegalArgumentException("Dados da Turma inválidos");
        }

        if (!turmaRepository.existsById(id)) {
            throw new IllegalArgumentException("Turma não encontrada com o ID: " + id);
        }

        return convertToTurmaResponseDTO(turmaRepository.save(convertToTurma(turmaRequestDTO)));
    }

    @Override
    public TurmaResponseDTO create(TurmaRequestDTO turmaRequestDTO) {
        if (turmaRequestDTO == null || turmaRequestDTO.getNome() == null || turmaRequestDTO.getProfessorId() == null) {
            throw new IllegalArgumentException("Dados da Turma inválidos");
        }

        return convertToTurmaResponseDTO(turmaRepository.save(convertToTurma(turmaRequestDTO)));
    }

    @Override
    public void deleteById(Long aLong) {
        if (!turmaRepository.existsById(aLong)) {
            throw new IllegalArgumentException("Turma não encontrada com o ID: " + aLong);
        }

        turmaRepository.deleteById(aLong);
    }

    private TurmaResponseDTO convertToTurmaResponseDTO(Turma turma) {
        if (turma == null) {
            return null;
        }

        return new TurmaResponseDTO(
                turma.getNome(),
                turma.getProfessor().getId()
        );
    }

    private Turma convertToTurma(TurmaRequestDTO turmaRequestDTO) {
        if (turmaRequestDTO == null) {
            return null;
        }

        Turma turma = new Turma();
        turma.setNome(turmaRequestDTO.getNome());
        turma.setProfessor(usuarioRepository.findById(turmaRequestDTO.getProfessorId()).orElseThrow(
                () -> new IllegalArgumentException("Professor não encontrado com o ID: " + turmaRequestDTO.getProfessorId())
        ));

        return turma;
    }
}
