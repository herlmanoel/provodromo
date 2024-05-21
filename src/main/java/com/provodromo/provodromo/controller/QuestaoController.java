package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.QuestaoDTO;
import com.provodromo.provodromo.service.QuestaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @Override
    public QuestaoDTO criar(@Valid @RequestBody QuestaoDTO questaoDTO) {

        return questaoService.create(questaoDTO);
    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public QuestaoDTO buscar(@PathVariable Long id) {
        return questaoService.findById(id);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public QuestaoDTO atualizar(@PathVariable Long id, @Valid @RequestBody QuestaoDTO questaoDTO) {

        return questaoService.update(id, questaoDTO);
    }

    @GetMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void excluir(@PathVariable Long id) {
        questaoService.deleteById(id);
    }
}
