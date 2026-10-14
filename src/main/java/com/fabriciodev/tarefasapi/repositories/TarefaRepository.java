package com.fabriciodev.tarefasapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabriciodev.tarefasapi.entities.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT obj FROM Tarefa obj WHERE LOWER(obj.titulo) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Tarefa> searchByTitulo(String name, Pageable pageable);
}
