package com.fabriciodev.tarefasapi.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor 
public class FieldMessage {

    private String name;
    private String message;

}
