package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.model.Prova;
import com.provodromo.provodromo.service.ProvaService;
import com.provodromo.provodromo.service.QuestaoService;
import com.provodromo.provodromo.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/prova", produces = {"application/json"})
public class ProvaController {

    @Autowired
    private ProvaService provaService;

    @Autowired
    private TurmaService turmaService;

    @Autowired
    private QuestaoService questaoService;

    @GetMapping("/listar")
    public String listarProvas(Model model) {
        model.addAttribute("provas" , provaService.findAll());
        return "prova/listar";
    }
    @GetMapping("/form")
    public String novaProvaFormulario(Model model) {
        model.addAttribute("prova", new Prova());
        model.addAttribute("turmas", turmaService.findAll());
        model.addAttribute("questoes", questaoService.findAll());


        return "prova/form";
    }

    @PostMapping("/salvar")
    public String salvarProva(@ModelAttribute Prova prova) {
        provaService.save(prova);
        return "redirect:/prova/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarProvaFormulario(@PathVariable Long id, Model model) {
        Prova prova = provaService.findById(id);
        model.addAttribute("prova", prova);
        model.addAttribute("turmas", turmaService.findAll());
        model.addAttribute("questoes", questaoService.findAll());

        return "prova/form";
    }

    @PostMapping("/editar/{id}")
    public String editarProva(@PathVariable Long id, Prova prova) {
        prova.setId(id);
        provaService.save(prova);
        return "redirect:/prova/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProva(@PathVariable Long id, Model model) {
        provaService.deleteById(id);
        return "redirect:/prova/listar";
    }

    @GetMapping("/visualizar/{id}")
    public String visualizarProva(@PathVariable Long id, Model model) {
        Prova prova = provaService.findById(id);
        model.addAttribute("prova", prova);

        return "prova/visualizar";
    }

}
