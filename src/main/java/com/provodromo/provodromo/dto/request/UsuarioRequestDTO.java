package com.provodromo.provodromo.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
        private Long id;

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        private String nome;

        @NotBlank(message = "O email não pode estar em branco")
        @Size(max = 255, message = "O email não pode ter mais de 255 caracteres")
        private String email;

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, max = 255, message = "A senha deve ter entre 6 e 255 caracteres")
        private String senha;

        private TipoUsuarioRequestDTO tipoUsuario;
}
