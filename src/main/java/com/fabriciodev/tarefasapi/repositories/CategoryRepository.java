package com.fabriciodev.tarefasapi.repositories;

import com.fabriciodev.tarefasapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
