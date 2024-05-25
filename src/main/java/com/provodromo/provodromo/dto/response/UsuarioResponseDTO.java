package com.provodromo.provodromo.dto.response;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
        private Long id;
        private String nome;
        private String email;
        private String tipoUsuario;
}
