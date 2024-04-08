package com.provodromo.provodromo.service;

import com.provodromo.provodromo.model.Alternativa;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.repository.AlternativaRepository;
import com.provodromo.provodromo.repository.QuestaoRepository;
import com.provodromo.provodromo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class QuestaoService implements BaseService<Questao> {

    @Autowired
    private QuestaoRepository questaoRepository;
    @Autowired
    private AlternativaRepository alternativaRepository;


    @Override
    public Questao findById(Long id) {
        return questaoRepository.getReferenceById(id);
    }

    @Override
    public Set<Questao> findAll() {
        return Set.copyOf(questaoRepository.findAll());
    }

    @Override
    public Questao save(Questao entity) {
        List<Alternativa> alternativas = entity.getAlternativas();
        entity.setAlternativas(null);
        Questao questao = questaoRepository.save(entity);
        alternativas.forEach(alternativa -> alternativa.setQuestao(entity));
        alternativaRepository.saveAll(alternativas);

        return questao;
    }

    public Questao update(Questao entity) {
        return questaoRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        questaoRepository.deleteById(id);
    }
}
