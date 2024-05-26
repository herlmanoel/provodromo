package com.provodromo.provodromo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DadosPessoaisResponseDTO {
    private Long id;
    private Date dataNascimento;
    private String cpf;
    private String telefone;
    private String uf;
    private String cidade;
    private String rua;
    private Integer numero;
    private String complemento;
}
