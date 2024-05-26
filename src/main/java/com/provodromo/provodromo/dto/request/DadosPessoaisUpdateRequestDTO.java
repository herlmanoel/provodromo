package com.provodromo.provodromo.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisUpdateRequestDTO {

        @Past(message = "A data de nascimento deve ser no passado")
        private Date dataNascimento;

        @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
        private String telefone;

        @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
        private String uf;

        @Size(max = 100, message = "A cidade não pode ter mais de 100 caracteres")
        private String cidade;

        @Size(max = 255, message = "A rua não pode ter mais de 255 caracteres")
        private String rua;

        private Integer numero;

        private String complemento;
}
