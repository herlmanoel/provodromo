 package com.provodromo.provodromo.controller;

 import com.provodromo.provodromo.controller.base.BaseController;
 import com.provodromo.provodromo.dto.UsuarioDTO;
 import com.provodromo.provodromo.service.UsuarioService;
 import jakarta.validation.Valid;
 import lombok.AllArgsConstructor;
 import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.*;

 import java.util.ArrayList;
 import java.util.List;

 @RequestMapping("/api/usuario")
 @AllArgsConstructor
 @RestController
 public class UsuarioController implements BaseController<UsuarioDTO> {

     private final UsuarioService usuarioService;

     @GetMapping
     @Override
     public List<UsuarioDTO> listar() {
         return new ArrayList<>(usuarioService.findAll());
     }

     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     @Override
     public UsuarioDTO criar(@Valid @RequestBody UsuarioDTO usuario) {
         return usuarioService.save(usuario);
     }

     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     @Override
     public UsuarioDTO buscar(@PathVariable Long id) {
         return usuarioService.findById(id);
     }

     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     @Override
     public UsuarioDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuario) {
         return usuarioService.update(id, usuario);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     @Override
     public void excluir(@PathVariable Long id) {
         usuarioService.deleteById(id);
     }

     @PutMapping("/{usuarioId}/tipo/{tipoUsuarioId}")
     @ResponseStatus(HttpStatus.OK)
     public UsuarioDTO associarTipoUsuario(@PathVariable Long usuarioId, @PathVariable Long tipoUsuarioId) {
         return usuarioService.associarTipoUsuario(usuarioId, tipoUsuarioId);
     }
 }
