package com.fabriciodev.tarefasapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabriciodev.tarefasapi.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT obj FROM Task obj WHERE LOWER(obj.title) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Task> searchByTitle(String name, Pageable pageable);
}
