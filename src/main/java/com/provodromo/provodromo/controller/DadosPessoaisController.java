package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.dto.request.DadosPessoaisRequestDTO;
import com.provodromo.provodromo.dto.request.DadosPessoaisUpdateRequestDTO;
import com.provodromo.provodromo.dto.response.DadosPessoaisResponseDTO;
import com.provodromo.provodromo.service.DadosPessoaisService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/dadosPessoais", produces = {"application/json"})
@AllArgsConstructor
public class DadosPessoaisController {

    private final DadosPessoaisService dadosPessoaisService;

    @PostMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.CREATED)
    public DadosPessoaisResponseDTO criar(@PathVariable Long idUsuario, @Valid @RequestBody DadosPessoaisRequestDTO dados) {
        return dadosPessoaisService.create(idUsuario,dados);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DadosPessoaisResponseDTO buscar(@PathVariable Long id) {
        return dadosPessoaisService.findById(id);
    }

    @GetMapping("/nome")
    @ResponseStatus(HttpStatus.OK)
    public DadosPessoaisResponseDTO buscarByNomeUsuario(@RequestBody String nome) {
        return dadosPessoaisService.findByNomeUsuario(nome);
    }

    @GetMapping("/cpf")
    @ResponseStatus(HttpStatus.OK)
    public DadosPessoaisResponseDTO buscarByCpf(@RequestBody String cpf) {
        return dadosPessoaisService.findByCpf(cpf);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DadosPessoaisResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody DadosPessoaisUpdateRequestDTO dados) {
        return dadosPessoaisService.update(id, dados);
    }
}
