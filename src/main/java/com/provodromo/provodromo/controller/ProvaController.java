package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.ProvaDTO;
import com.provodromo.provodromo.service.ProvaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/prova", produces = {"application/json"})
public class ProvaController implements BaseController<ProvaDTO> {

    @Autowired
    private ProvaService provaService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Set<ProvaDTO> listar() {
        return provaService.findAll();
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ProvaDTO criar(@Valid @RequestBody ProvaDTO provaDTO) {
        return provaService.create(provaDTO);
    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ProvaDTO buscar(@PathVariable Long id) {
        return provaService.findById(id);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ProvaDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProvaDTO provaDTO) {
        return provaService.update(id, provaDTO);
    }

    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void excluir(@PathVariable Long id) {
        provaService.deleteById(id);
    }
}
