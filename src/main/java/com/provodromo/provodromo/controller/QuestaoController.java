package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.model.Alternativa;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/questao", produces = {"application/json"})
public class QuestaoController {

    @Autowired
    private QuestaoService questaoService;

    @GetMapping("/listar")
    public String listarQuestao(Model model) {
        model.addAttribute("questoes", questaoService.findAll());
        return "questao/listar";
    }

    @GetMapping("/novo")
    public String novaQuestaoForm(Model model) {
        Questao questao = new Questao();
        Alternativa alternativa1 = new Alternativa();
        Alternativa alternativa2 = new Alternativa();
        Alternativa alternativa3 = new Alternativa();
        Alternativa alternativa4 = new Alternativa();
        alternativa1.setQuestao(questao);
        alternativa2.setQuestao(questao);
        alternativa3.setQuestao(questao);
        alternativa4.setQuestao(questao);
        List<Alternativa> alternativas = List.of(alternativa1, alternativa2, alternativa3, alternativa4);
        questao.setAlternativas(alternativas);
        model.addAttribute("questao", questao);
        return "questao/form";
    }

    @PostMapping("/salvar")
    public String salvarQuestao(@ModelAttribute Questao questao) {
        if (questao.getId() != null) {
            questaoService.update(questao);
        } else {
            questaoService.save(questao);
        }

        return "redirect:/questao/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarQuestaoForm(@PathVariable Long id, Model model) {
        Questao questao = questaoService.findById(id);
        model.addAttribute("questao", questao);
        return "questao/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarQuestao(@PathVariable Long id, @ModelAttribute Questao questao) {
        questao.setId(id); // Certifique-se de que o ID está definido corretamente
        questaoService.save(questao);
        return "redirect:/questao/listar"; // Redirecionar para a página de listagem após a atualização
    }

    @GetMapping("/excluir/{id}")
    public String excluirQuestao(@PathVariable Long id) {
        questaoService.deleteById(id);
        return "redirect:/questao/listar"; // Redirecionar para a página de listagem após a exclusão
    }
}
