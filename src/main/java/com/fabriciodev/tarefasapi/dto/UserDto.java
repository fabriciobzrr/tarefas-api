package com.fabriciodev.tarefasapi.dto;

import com.fabriciodev.tarefasapi.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;

    private List<String> roles = new ArrayList<>();

    public UserDto(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();

        for (GrantedAuthority role : entity.getRoles()) {
            roles.add(role.getAuthority());
        }
    }
}
