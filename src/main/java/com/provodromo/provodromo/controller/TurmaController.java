package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.model.Turma;
import com.provodromo.provodromo.model.Usuario;
import com.provodromo.provodromo.service.TipoUsuarioService;
import com.provodromo.provodromo.service.TurmaService;
import com.provodromo.provodromo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/turma")
public class TurmaController {
//    @Autowired
//    private TurmaService turmaService;
//
//    @Autowired
//    private UsuarioService usuarioService;
//
//    @Autowired
//    private TipoUsuarioService tipoUsuarioService;
//
//    @GetMapping("/listar")
//    public String listar(Model model) {
//        model.addAttribute("turmas" , turmaService.findAll());
//
//        return "turma/listar";
//    }
//
//    @GetMapping("/form")
//    public String novaTurmaFormulario(Model model) {
//        model.addAttribute("turma", new Turma());
//        model.addAttribute("professores",
//                usuarioService.findByTipoUsuario(
//                        tipoUsuarioService.findByName("Professor").getId()));
//        return "turma/form";
//    }
//
//    @PostMapping("salvar")
//    public String salvarTurma(@ModelAttribute Turma turma) {
//        turmaService.save(turma);
//        return "turma/listar";
//    }
//
//    @GetMapping("/editar/{id}")
//    public String editarTurmaFormulario(@PathVariable Long id, Model model) {
//        Turma turma = turmaService.findById(id);
//        model.addAttribute("turma", turma);
//        model.addAttribute("professores",
//                usuarioService.findByTipoUsuario(
//                        tipoUsuarioService.findByName("Professor").getId()));
//        return "turma/form";
//    }
//
//    @PostMapping("/editar/{id}")
//    public String editarTurma(@PathVariable Long id, Turma turma) {
//        turma.setId(id);
//        turmaService.save(turma);
//        return "/turma/listar";
//    }
//
//    @GetMapping("/excluir/{id}")
//    public String excluirturma(@PathVariable Long id, Model model) {
//        turmaService.deleteById(id);
//        return "/turma/listar";
//    }

}
