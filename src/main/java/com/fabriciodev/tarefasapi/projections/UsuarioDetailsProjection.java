package com.fabriciodev.tarefasapi.projections;

public interface UsuarioDetailsProjection {

    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();

}
