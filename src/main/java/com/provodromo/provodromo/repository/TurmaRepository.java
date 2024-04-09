package com.provodromo.provodromo.repository;

import com.provodromo.provodromo.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    Turma findByNome(String nome);
}
