package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.TurmaDTO;
import com.provodromo.provodromo.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/turma", produces = {"application/json"})
public class TurmaController implements BaseController<TurmaDTO> {
    @Autowired
    private TurmaService turmaService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Set<TurmaDTO> listar() {
        return turmaService.findAll();
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public TurmaDTO criar(@Valid @RequestBody TurmaDTO turmaDTO) {
        return turmaService.create(turmaDTO);
    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public TurmaDTO buscar(@PathVariable Long id) {
        return turmaService.findById(id);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public TurmaDTO atualizar(@PathVariable Long id, @Valid @RequestBody TurmaDTO turmaDTO) {
        return turmaService.update(id, turmaDTO);
    }

    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void excluir(@PathVariable Long id) {
        turmaService.deleteById(id);
    }
}
