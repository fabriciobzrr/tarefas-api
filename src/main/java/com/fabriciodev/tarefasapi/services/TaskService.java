package com.fabriciodev.tarefasapi.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabriciodev.tarefasapi.dto.TaskDto;
import com.fabriciodev.tarefasapi.entities.Task;
import com.fabriciodev.tarefasapi.repositories.TaskRepository;
import com.fabriciodev.tarefasapi.services.exceptions.DatabaseException;
import com.fabriciodev.tarefasapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class TaskService {
    
    private final TaskRepository repository;

    @Transactional(readOnly = true)
    public Page<TaskDto> searchByTitle(String title, Pageable pageable) {
        Page<Task> result = repository.searchByTitle(title, pageable);
        return result.map(x -> new TaskDto(x));
    }

    @Transactional(readOnly = true)
    public TaskDto findById(Long id) {
        Task result = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada! Id: " + id + "."));
        return new TaskDto(result);
    }

    @Transactional
    public TaskDto insert(TaskDto dto) {
        Task entity = new Task();
        updateData(dto, entity);
        entity = repository.save(entity);
        return new TaskDto(entity);
    }
    
    @Transactional
    public TaskDto update(Long id, TaskDto dto) {
        try {
            Task entity = repository.getReferenceById(id);
            updateData(dto, entity);
            repository.save(entity);
            return new TaskDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não localizada! Id: " + id + ".");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tarefa não localizada! Id: " + id + ".");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("A tarefa não pode ser excluída. (Falha de integridade relacional)");
        }
    }

    private void updateData(TaskDto dto, Task entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());

        if (dto.getFinished() != null) {
            entity.setFinished(dto.getFinished());
        } else {
            entity.setFinished(false);
        }
    }
}
