package com.provodromo.provodromo.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisRequestDTO {
        private Long id;

        @NotNull(message = "A data de nascimento não pode estar nula")
        @Past(message = "A data de nascimento deve ser no passado")
        private Date dataNascimento;

        @NotBlank(message = "O CPF não pode estar em branco")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
        private String cpf;

        @NotBlank(message = "O telefone não pode estar em branco")
        @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
        private String telefone;

        @NotBlank(message = "A UF não pode estar em branco")
        @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
        private String uf;

        @NotBlank(message = "A cidade não pode estar em branco")
        @Size(max = 100, message = "A cidade não pode ter mais de 100 caracteres")
        private String cidade;

        @NotBlank(message = "A rua não pode estar em branco")
        @Size(max = 255, message = "A rua não pode ter mais de 255 caracteres")
        private String rua;

        @NotNull(message = "O número não pode estar nulo")
        private Integer numero;

        private String complemento;
}
