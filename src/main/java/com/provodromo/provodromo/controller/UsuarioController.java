 package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.UsuarioDTO;
import com.provodromo.provodromo.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 @RequestMapping("/usuario")
 @AllArgsConstructor
 @RestController
 public class UsuarioController implements BaseController<UsuarioDTO> {

     private final UsuarioService usuarioService;

     @GetMapping
     @Override
     public List<UsuarioDTO> listar() {
         return usuarioService.findAll().stream().toList();
     }

     @PostMapping
     @Override
     public UsuarioDTO criar(@Valid @RequestBody UsuarioDTO usuario) {
         return usuarioService.save(usuario);
     }

     @GetMapping("/{id}")
     @Override
     public UsuarioDTO buscar(@PathVariable Long id) {
         return usuarioService.findById(id);
     }

     @PutMapping("/{id}")
     @Override
     public UsuarioDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuario) {
         return usuarioService.update(id, usuario);
     }

     @DeleteMapping("/{id}")
     @Override
     public void excluir(@PathVariable Long id) {
         usuarioService.deleteById(id);
     }

     @PostMapping("/{usuarioId}/tipo/{tipoUsuarioId}")
     public void associarTipoUsuario(@PathVariable Long usuarioId, @PathVariable Long tipoUsuarioId) {
         usuarioService.associarTipoUsuario(usuarioId, tipoUsuarioId);
     }
 }

