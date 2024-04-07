package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.model.Turma;
import com.provodromo.provodromo.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @GetMapping("/listarTurmas")
    public String listarTurmas(Model model) {
        model.addAttribute("turmas" , turmaService.findAll());
        return "turma/listarTurmas";
    }

    //TODO filtrar procas
    @GetMapping("/listarTurmasAtivas")
    public String listarTurmasAtivas (Model model) {
        model.addAttribute("turmasAtivas", turmaService.findAll());
        return  "turma/listarTurma";
    }

    @GetMapping("/formularioturma")
    public String novaturmaFormulario(Model model) {
        model.addAttribute("turma", new Turma());
        return "turma/formularioturma";
    }

    @PostMapping("salvarTurma")
    public String salvarTurma(@ModelAttribute Turma turma) {
        turmaService.save(turma);
        return "turma/listarTurma";
    }

    @GetMapping("/formularioEdicao/{id}")
    public String editarTurmaFormulario(@PathVariable Long id, Model model) {
        Turma turma = turmaService.findById(id);
        model.addAttribute("turma", turma);
        return "turma/formulario";
    }

    @PostMapping("/editar/{id}")
    public String editarTurma(@PathVariable Long id, Turma turma) {
        turma.setId(id);
        turmaService.save(turma);
        return "redirect:/turma/listarTurma";
    }

    @GetMapping("/excluir/{id}")
    public String excluirturma(@PathVariable Long id, Model model) {
        turmaService.deleteById(id);
        return "redirect:/turma/listarTurma";
    }

}
