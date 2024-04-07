package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_turma")
@Data
public class Turma extends BaseModel {
    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario professor;
}