package com.provodromo.provodromo.controller;


import com.provodromo.provodromo.dto.request.LoginRequestDTO;
import com.provodromo.provodromo.dto.response.LoginResponseDTO;
import com.provodromo.provodromo.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService service;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO body){
        return service.login(body);
    }
}
