package com.fabriciodev.tarefasapi.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_role")
@Getter 
@Setter 
@NoArgsConstructor  
public class Role implements GrantedAuthority {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;

    @ManyToMany(mappedBy = "roles")
    @Setter(AccessLevel.NONE)
    private Set<User> users = new HashSet<>();

    public Role(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    @Override 
    public String getAuthority() {
        return authority;
    }
}
