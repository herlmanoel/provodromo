package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.TipoUsuarioDTO;
import com.provodromo.provodromo.service.TipoUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tipoUsuarios", produces = {"application/json"})
@AllArgsConstructor
public class TipoUsuarioController implements BaseController<TipoUsuarioDTO> {

    private TipoUsuarioService tipoUsuarioService;


    @GetMapping
    public List<TipoUsuarioDTO> listar() {
        return tipoUsuarioService.findAll().stream().toList();
    }

    @PostMapping
    public TipoUsuarioDTO criar(@Valid @RequestBody TipoUsuarioDTO tipoUsuario) {
        return tipoUsuarioService.create(tipoUsuario);
    }

    @GetMapping("/{id}")
    public TipoUsuarioDTO buscar(@PathVariable Long id) {
        return tipoUsuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public TipoUsuarioDTO atualizar(@PathVariable Long id, @Valid @RequestBody TipoUsuarioDTO tipoUsuario) {
        return tipoUsuarioService.update(id, tipoUsuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        tipoUsuarioService.deleteById(id);
    }
}

