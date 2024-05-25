package com.provodromo.provodromo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDTO {
    private String nome;
    private String token;
    private String email;
    private String senha;
}
