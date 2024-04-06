 package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.model.Usuario;
import com.provodromo.provodromo.service.TipoUsuarioService;
import com.provodromo.provodromo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuario/listar";
    }

    @GetMapping("/novo")
    public String novo(@RequestParam(required = false) Long id, Model model) {
        Usuario usuario = id != null ? usuarioService.findById(id) : new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("tiposUsuarios", tipoUsuarioService.findAll());
        return "usuario/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuario/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("tiposUsuarios", tipoUsuarioService.findAll());
        return "usuario/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuario/listar";
    }
}

