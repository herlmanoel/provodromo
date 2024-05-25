package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.HistoricoTipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoTipoUsuarioRepository extends JpaRepository<HistoricoTipoUsuario, Long> {
}
