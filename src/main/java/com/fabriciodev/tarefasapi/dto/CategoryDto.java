package com.fabriciodev.tarefasapi.dto;

import com.fabriciodev.tarefasapi.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
