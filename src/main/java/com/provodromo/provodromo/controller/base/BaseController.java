package com.provodromo.provodromo.controller.base;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseController<T> {

    @GetMapping
    List<T> listar();

    @PostMapping
    T criar(@RequestBody T objeto);

    @GetMapping("/{id}")
    T buscar(@PathVariable Long id);

    @PutMapping("/{id}")
    T atualizar(@PathVariable Long id, @RequestBody T objeto);

    @DeleteMapping("/{id}")
    void excluir(@PathVariable Long id);
}
