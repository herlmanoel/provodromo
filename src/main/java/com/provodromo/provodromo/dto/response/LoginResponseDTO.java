package com.provodromo.provodromo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDTO {
    private String token;
    private Instant expiracao;
}
