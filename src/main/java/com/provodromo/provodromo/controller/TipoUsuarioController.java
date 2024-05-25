package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.request.TipoUsuarioRequestDTO;
import com.provodromo.provodromo.dto.response.TipoUsuarioResponseDTO;
import com.provodromo.provodromo.service.TipoUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/tipoUsuario", produces = {"application/json"})
@AllArgsConstructor
public class TipoUsuarioController implements BaseController<TipoUsuarioRequestDTO, TipoUsuarioResponseDTO> {

    private TipoUsuarioService tipoUsuarioService;


    @GetMapping
    public Set<TipoUsuarioResponseDTO> listar() {
        return tipoUsuarioService.findAll();
    }

    @PostMapping
    public TipoUsuarioResponseDTO criar(@Valid @RequestBody TipoUsuarioRequestDTO tipoUsuario) {
        return tipoUsuarioService.create(tipoUsuario);
    }

    @GetMapping("/{id}")
    public TipoUsuarioResponseDTO buscar(@PathVariable Long id) {
        return tipoUsuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public TipoUsuarioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TipoUsuarioRequestDTO tipoUsuario) {
        return tipoUsuarioService.update(id, tipoUsuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        tipoUsuarioService.deleteById(id);
    }
}

