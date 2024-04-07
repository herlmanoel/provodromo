package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.model.Prova;
import com.provodromo.provodromo.service.ProvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/prova")
public class ProvaController {

    @Autowired
    private ProvaService provaService;

    @GetMapping("/listarProvas")
    public String listarProvas(Model model) {
        model.addAttribute("provas" , provaService.findAll());
        return "prova/listar";
    }

    //TODO filtrar procas
    @GetMapping("/listarProvasAtivas")
    public String listarProvasAtivas (Model model) {
        model.addAttribute("provasAtivas", provaService.findAll());
        return  "prova/listar";
    }

    @GetMapping("/formularioProva")
    public String novaProvaFormulario(Model model) {
        model.addAttribute("prova", new Prova());
        return "prova/formulario";
    }

    @PostMapping("salvarProva")
    public String salvarProva(@ModelAttribute Prova prova) {
        provaService.save(prova);
        return "questao/formulario";
    }

    @GetMapping("/formularioEdicao/{id}")
    public String editarProvaFormulario(@PathVariable Long id, Model model) {
        Prova prova = provaService.findById(id);
        model.addAttribute("prova", prova);
        return "prova/formulario";
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

}
