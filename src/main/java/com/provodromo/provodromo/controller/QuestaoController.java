package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.QuestaoDTO;
import com.provodromo.provodromo.model.Alternativa;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.service.QuestaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/questao", produces = {"application/json"})
public class QuestaoController implements BaseController<QuestaoDTO> {

    @Autowired
    private QuestaoService questaoService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Set<QuestaoDTO> listar() {
        return questaoService.findAll();
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestaoDTO criar(@Valid @RequestBody QuestaoDTO questaoDTO) {

        return questaoService.create(questaoDTO);
    }

    @Override
    public QuestaoDTO buscar(Long id) {
        return questaoService.findById(id);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuestaoDTO atualizar(@PathVariable Long id, @Valid @RequestBody QuestaoDTO questaoDTO) {

        return questaoService.update(id, questaoDTO);
    }

    @GetMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        questaoService.deleteById(id);
    }
}
