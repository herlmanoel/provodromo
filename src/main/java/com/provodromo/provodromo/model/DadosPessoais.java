package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "tb_dados_pessoais")
@Data
public class DadosPessoais extends BaseModel {
    @OneToOne(mappedBy = "dadosPessoais")
    private Usuario usuario;

    private Date dataNascimento;
    private String cpf;
    private String telefone;

    private String uf;
    private String cidade;
    private String rua;
    private Integer numero;
    private String complemento;

}
