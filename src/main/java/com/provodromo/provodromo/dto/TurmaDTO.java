package com.provodromo.provodromo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TurmaDTO {
    private String nome;
    private Long professorId;
}
