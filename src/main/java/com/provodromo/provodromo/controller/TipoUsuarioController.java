package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipoUsuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping("/listar")
    public String listarTiposUsuarios(Model model) {
        model.addAttribute("tiposUsuarios", tipoUsuarioService.findAll());
        return "tipoUsuario/listar";
    }

    @GetMapping("/novo")
    public String novoTipoUsuarioForm(Model model) {
        model.addAttribute("tipoUsuario", new TipoUsuario());
        return "tipoUsuario/form";
    }

    @PostMapping("/salvar")
    public String salvarTipoUsuario(@ModelAttribute TipoUsuario tipoUsuario) {
        tipoUsuarioService.save(tipoUsuario);
        return "redirect:/tipoUsuario/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarTipoUsuarioForm(@PathVariable Long id, Model model) {
        TipoUsuario tipoUsuario = tipoUsuarioService.findById(id);
        model.addAttribute("tipoUsuario", tipoUsuario);
        return "tipoUsuario/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarTipoUsuario(@PathVariable Long id, @ModelAttribute TipoUsuario tipoUsuario) {
        tipoUsuario.setId(id);
        tipoUsuarioService.save(tipoUsuario);
        return "redirect:/tipoUsuario/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirTipoUsuario(@PathVariable Long id) {
        tipoUsuarioService.deleteById(id);
        return "redirect:/tipoUsuario/listar";
    }
}
