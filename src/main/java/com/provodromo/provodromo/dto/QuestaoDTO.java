package com.provodromo.provodromo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestaoDTO {
    private Long id;
    private String texto;
    private String dificuldade;
    private double nota;
    private List<AlternativaDTO> alternativas = new ArrayList<>();
}
