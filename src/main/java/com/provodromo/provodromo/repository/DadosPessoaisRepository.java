package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.DadosPessoais;
import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Long> {
    DadosPessoais getByCpf(String nome);
    DadosPessoais findByUsuarioId(Long id);
    DadosPessoais findByUsuarioNome(String nome);
    Boolean existsByCpf(String cpf);

}
