package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.AlternativaDTO;
import com.provodromo.provodromo.dto.QuestaoDTO;
import com.provodromo.provodromo.error.exception.RegraNegocioException;
import com.provodromo.provodromo.model.Alternativa;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.repository.AlternativaRepository;
import com.provodromo.provodromo.repository.QuestaoRepository;
import com.provodromo.provodromo.service.base.BaseServiceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuestaoService implements BaseServiceNew<QuestaoDTO, Long> {

    @Autowired
    private QuestaoRepository questaoRepository;
    @Autowired
    private AlternativaRepository alternativaRepository;


    @Override
    public QuestaoDTO findById(Long id) {
        Questao questao = questaoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Questão não encontrada com o ID: " + id));

        return entityToDTO(questao);
    }

    @Override
    public Set<QuestaoDTO> findAll() {
        return questaoRepository.findAll().stream().map(this::entityToDTO).collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Long id) {
        if (!questaoRepository.existsById(id)) {
            throw new RegraNegocioException("Questão não encontrada com o ID: " + id);
        }
        questaoRepository.deleteById(id);

    }

    @Override
    public QuestaoDTO create(QuestaoDTO questaoDTO) {
        if (questaoDTO == null || questaoDTO.getTexto() == null || questaoDTO.getAlternativas() == null) {
            throw new IllegalArgumentException("Dados da Questão inválidos");
        }

        return createOrUpdate(questaoDTO);
    }

    @Override
    public QuestaoDTO update(Long id, QuestaoDTO questaoDTO) {
        if (questaoDTO == null || questaoDTO.getTexto() == null || questaoDTO.getAlternativas() == null) {
            throw new RegraNegocioException("Dados da Questão inválidos");
        }

        if (!questaoRepository.existsById(id)) {
            throw new RegraNegocioException("Questão não encontrada com o ID: " + id);
        }

        questaoDTO.setId(id);

        return createOrUpdate(questaoDTO);
    }

    public QuestaoDTO createOrUpdate(QuestaoDTO questaoDTO) {
        if (questaoDTO == null || questaoDTO.getTexto() == null) {
            throw new IllegalArgumentException("Dados da Questão inválidos");
        }
        List<Alternativa> alternativas = questaoDTO.getAlternativas().stream().map(this::convertToAlternativa).toList();
        questaoDTO.setAlternativas(null);
        Questao questao = dtoToEntity(questaoDTO);
        questaoRepository.save(questao);
        alternativas.forEach(alternativa -> alternativa.setQuestao(questao));
        alternativaRepository.saveAll(alternativas);
        questao.setAlternativas(alternativas);

        return entityToDTO(questao);
    }

    private QuestaoDTO entityToDTO(Questao questao) {
        if (questao == null) {
            return null;
        }
        List<AlternativaDTO> alternativasDTO = questao.getAlternativas().stream()
                .map(this::converToAlternativaDTO)
                .toList();

        return new QuestaoDTO(
                questao.getId(),
                questao.getTexto(),
                questao.getDificuldade(),
                questao.getNota(),
                alternativasDTO
        );
    }

    private Questao dtoToEntity(QuestaoDTO questaoDTO) {
        if (questaoDTO == null) {
            return null;
        }

        Questao questao = new Questao();
        questao.setId(questaoDTO.getId());
        questao.setTexto(questaoDTO.getTexto());
        questao.setDificuldade(questaoDTO.getDificuldade());
        questao.setNota(questaoDTO.getNota());
        if (questaoDTO.getAlternativas() != null) {
            List<Alternativa> alternativas = questaoDTO.getAlternativas().stream()
                    .map(this::convertToAlternativa)
                    .toList();
            questao.setAlternativas(alternativas);
        }

        return questao;
    }

    private AlternativaDTO converToAlternativaDTO(Alternativa alternativa) {
        if (alternativa == null) {
            return null;
        }
        return new AlternativaDTO(alternativa.getId(), alternativa.getTexto(), alternativa.isCorreta());
    }

    private Alternativa convertToAlternativa(AlternativaDTO alternativaDTO) {
        if (alternativaDTO == null) {
            return null;
        }
        Alternativa alternativa = new Alternativa();
        alternativa.setId(alternativaDTO.getId());
        alternativa.setTexto(alternativaDTO.getTexto());
        alternativa.setCorreta(alternativaDTO.isCorreta());
        return alternativa;
    }
}
