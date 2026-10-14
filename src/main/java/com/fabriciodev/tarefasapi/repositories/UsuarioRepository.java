package com.fabriciodev.tarefasapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabriciodev.tarefasapi.entities.Usuario;
import com.fabriciodev.tarefasapi.projections.UsuarioDetailsProjection;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_usuario.email AS username, tb_usuario.password, tb_role.id AS roleId, tb_role.authority\n" +
        "FROM tb_usuario\n" +
        "INNER JOIN tb_usuario_role ON tb_usuario.id = tb_usuario_role.usuario_id\n" +
        "INNER JOIN tb_role ON tb_role.id = tb_usuario_role.role_id\n" +
        "WHERE tb_usuario.email = :email;")
    List<UsuarioDetailsProjection> searchUserAndRoleByEmail(String email);

    Usuario findByEmail(String email);
}
