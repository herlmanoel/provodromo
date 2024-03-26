package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_questao")
@Data
public class Questao extends BaseModel {

    private String texto;
    private String dificuldade;

    @ManyToMany(mappedBy = "questoes")
    @EqualsAndHashCode.Exclude
    private Set<Prova> provas = new HashSet<>();
}