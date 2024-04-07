package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_turma")
@Data
public class Turma extends BaseModel {

    private String nome;
    private Usuario professor;
    private List<Usuario> alunos;
}