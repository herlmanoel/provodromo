package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_prova")
@Data
public class Prova extends BaseModel {

    private String titulo;
    private String dataInicio;
    private String dataFim;
    private int nota;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private Usuario criador;

    @ManyToMany
    @JoinTable(
            name = "tb_prova_questao",
            joinColumns = @JoinColumn(name = "prova_id"),
            inverseJoinColumns = @JoinColumn(name = "questao_id")
    )
    @EqualsAndHashCode.Exclude
    private Set<Questao> questoes = new HashSet<>();
}