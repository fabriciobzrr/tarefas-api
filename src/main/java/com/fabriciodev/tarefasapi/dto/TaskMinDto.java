package com.fabriciodev.tarefasapi.dto;

import com.fabriciodev.tarefasapi.entities.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskMinDto {
    private Long id;
    private String title;
    private Boolean finished;
    private CategoryDto category;

    public TaskMinDto(Long id, String title, Boolean finished, CategoryDto category) {
        this.id = id;
        this.title = title;
        this.finished = finished;
        this.category = category;
    }

    public TaskMinDto(Task task) {
        id = task.getId();
        title = task.getTitle();
        finished = task.getFinished();
        category = new CategoryDto(task.getCategory());
    }
}
