package com.fabriciodev.tarefasapi.controllers;

import com.fabriciodev.tarefasapi.dto.UserDto;
import com.fabriciodev.tarefasapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor 
public class UserController {

    private final UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GERENCIA', 'ROLE_COORDENACAO')")
    @GetMapping("/me")
    public ResponseEntity<UserDto> findMe() {
        UserDto dto = service.findMe();
        return ResponseEntity.ok().body(dto);
    }

}
