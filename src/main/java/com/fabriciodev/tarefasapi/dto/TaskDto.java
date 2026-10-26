package com.fabriciodev.tarefasapi.dto;

import com.fabriciodev.tarefasapi.entities.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskDto {
    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 4, max = 80, message = "Título deve ter entre 4 e 80 caracteres")
    private String title;

    @NotBlank(message = "Campo requerido")
    @Size(min = 10, message = "Descrição deve ter no mínimo 10 caracteres")
    private String description;

    private Boolean finished;
    private CategoryDto category;

    public TaskDto(Task entity) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        finished = entity.getFinished();
        category = entity.getCategory() != null ? new CategoryDto(entity.getCategory()) : null;
    }
}
