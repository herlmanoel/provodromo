package com.provodromo.provodromo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlternativaDTO {
    private Long id;
    private String texto;
    private boolean correta = false;
}
