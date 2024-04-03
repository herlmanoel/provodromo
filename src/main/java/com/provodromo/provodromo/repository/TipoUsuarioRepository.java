package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.TipoUsuario;
import com.provodromo.provodromo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    @Query("SELECT tu FROM TipoUsuario tu WHERE tu.nome = :nome")
    TipoUsuario findByName(@Param("nome") String nome);
}
