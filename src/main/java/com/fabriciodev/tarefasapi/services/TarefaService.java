package com.fabriciodev.tarefasapi.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabriciodev.tarefasapi.dto.TarefaDTO;
import com.fabriciodev.tarefasapi.entities.Tarefa;
import com.fabriciodev.tarefasapi.repositories.TarefaRepository;
import com.fabriciodev.tarefasapi.services.exceptions.DatabaseException;
import com.fabriciodev.tarefasapi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class TarefaService {
    
    private final TarefaRepository repository;

    @Transactional(readOnly = true)
    public Page<TarefaDTO> searchByTitulo(String titulo, Pageable pageable) {
        Page<Tarefa> resultado = repository.searchByTitulo(titulo, pageable);
        return resultado.map(x -> new TarefaDTO(x));
    }

    @Transactional(readOnly = true)
    public TarefaDTO findById(Long id) {
        Tarefa resultado = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada! Id: " + id + "."));
        return new TarefaDTO(resultado);
    }

    @Transactional
    public TarefaDTO insert(TarefaDTO dto) {
        Tarefa entity = new Tarefa();
        updateData(dto, entity);
        entity = repository.save(entity);
        return new TarefaDTO(entity);
    }
    
    @Transactional
    public TarefaDTO update(Long id, TarefaDTO dto) {
        try {
            Tarefa entity = repository.getReferenceById(id);
            updateData(dto, entity);
            repository.save(entity);
            return new TarefaDTO(entity);
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

    private void updateData(TarefaDTO dto, Tarefa entity) {
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());

        if (dto.getConcluida() != null) {
            entity.setConcluida(dto.getConcluida());
        } else {
            entity.setConcluida(false);
        }
    }
}
