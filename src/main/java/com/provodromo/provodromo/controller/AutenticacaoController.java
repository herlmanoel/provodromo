package com.provodromo.provodromo.controller;


import com.provodromo.provodromo.dto.LoginDTO;
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
    public LoginDTO login(@RequestBody LoginDTO body){
        String token = service.login(body);
        body.setToken(token);
        body.setSenha(null);
        return body;
    }

    @PostMapping("/register")
    public LoginDTO register(@RequestBody LoginDTO body){
        String token = service.register(body);
        body.setToken(token);
        body.setSenha(null);
        return body;
    }
}
