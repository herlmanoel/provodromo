package com.provodromo.provodromo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvaDTO {
    private Long id;
    private String titulo;
    private Long turmaId;
    private int nota;
    private Set<Long> questoesIds;
}
