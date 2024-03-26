package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario extends BaseModel {

    private String nome;
    private String email;
    private String senha;

    @ManyToOne
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuario tipoUsuario;
}
