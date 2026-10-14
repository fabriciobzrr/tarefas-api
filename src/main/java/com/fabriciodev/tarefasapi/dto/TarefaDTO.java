package com.fabriciodev.tarefasapi.dto;

import com.fabriciodev.tarefasapi.entities.Tarefa;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TarefaDTO {
    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 4, max = 80, message = "Título deve ter entre 4 e 80 caracteres")
    private String titulo;

    @NotBlank(message = "Campo requerido")
    @Size(min = 10, message = "Descrição deve ter no mínimo 10 caracteres")
    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Boolean concluida;

    public TarefaDTO(Tarefa tarefa) {
        id = tarefa.getId();
        titulo = tarefa.getTitulo();
        descricao = tarefa.getDescricao();
        concluida = tarefa.getConcluida();
    }
}
