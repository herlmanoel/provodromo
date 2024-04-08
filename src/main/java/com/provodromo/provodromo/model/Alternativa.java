package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_alternativa")
@Data
public class Alternativa extends BaseModel {

    private String texto;
    private boolean correta = false;

    @ManyToOne
    @JoinColumn(name = "questao_id")
    private Questao questao;
}
