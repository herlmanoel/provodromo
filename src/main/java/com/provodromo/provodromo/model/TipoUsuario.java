package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_tipo_usuario")
@Data
public class TipoUsuario extends BaseModel {

    private String nome;
}