package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.ProvaDTO;
import com.provodromo.provodromo.model.Prova;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.repository.ProvaRepository;
import com.provodromo.provodromo.repository.QuestaoRepository;
import com.provodromo.provodromo.repository.TurmaRepository;
import com.provodromo.provodromo.service.base.BaseServiceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProvaService implements BaseServiceNew<ProvaDTO, Long> {

    @Autowired
    private ProvaRepository provaRepository;
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private QuestaoRepository questaoRepository;

    @Override
    public ProvaDTO findById(Long id) {
        Prova prova = provaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada com o id: " + id));

        return entityToDTO(prova);
    }

    @Override
    public Set<ProvaDTO> findAll() {
        return provaRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public ProvaDTO update(Long aLong, ProvaDTO provaDTO) {
        if (provaDTO == null || provaDTO.getTitulo() == null || provaDTO.getQuestoesIds() == null || provaDTO.getTurmaId() == null) {
            throw new RuntimeException("Prova não encontrada com o id: " + aLong);
        }

        if (!provaRepository.existsById(aLong)) {
            throw new RuntimeException("Prova não encontrada com o id: " + aLong);
        }

        return entityToDTO(provaRepository.save(dtoToEntity(provaDTO)));
    }

    @Override
    public ProvaDTO create(ProvaDTO provaDTO) {
        if (provaDTO == null || provaDTO.getTitulo() == null || provaDTO.getQuestoesIds() == null || provaDTO.getTurmaId() == null) {
            throw new RuntimeException("Prova não encontrada com o id: " + provaDTO.getId());
        }

        return entityToDTO(provaRepository.save(dtoToEntity(provaDTO)));
    }

    @Override
    public void deleteById(Long aLong) {
        if (!provaRepository.existsById(aLong)) {
            throw new RuntimeException("Prova não encontrada com o id: " + aLong);
        }

        provaRepository.deleteById(aLong);
    }

    private ProvaDTO entityToDTO(Prova prova) {
        if (prova == null) {
            return null;
        }
        Set<Long> questoesIds = prova.getQuestoes().stream()
                .map(Questao::getId)
                .collect(Collectors.toSet());

        return new ProvaDTO(
                prova.getId(),
                prova.getTitulo(),
                prova.getTurma().getId(),
                prova.getNota(),
                questoesIds
        );
    }

    private Prova dtoToEntity(ProvaDTO provaDTO) {
        if (provaDTO == null) {
            return null;
        }

        Prova prova = new Prova();
        prova.setId(provaDTO.getId());
        prova.setTurma(turmaRepository.findById(provaDTO.getTurmaId()).orElseThrow(() -> new RuntimeException("Turma não encontrada com o id: " + provaDTO.getTurmaId())));
        prova.setNota(provaDTO.getNota());
        prova.setTitulo(provaDTO.getTitulo());
        prova.setQuestoes(provaDTO.getQuestoesIds().stream()
                .map(id -> questaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Questão não encontrada com o id: " + id)))
                .collect(Collectors.toSet()));

        return prova;
    }
}
