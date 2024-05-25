package com.provodromo.provodromo.model;

import com.provodromo.provodromo.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_hist_tipo_usuario")
@Data
public class HistoricoTipoUsuario extends BaseModel {

    private String nome;

    @CreatedDate
    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    private Long tipoUsuarioId;
}
