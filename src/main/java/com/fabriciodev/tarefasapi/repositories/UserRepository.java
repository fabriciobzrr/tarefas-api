package com.fabriciodev.tarefasapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fabriciodev.tarefasapi.entities.User;
import com.fabriciodev.tarefasapi.projections.UsuarioDetailsProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_user.email AS username, tb_user.password, tb_role.id AS roleId, tb_role.authority\n" +
        "FROM tb_user\n" +
        "INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id\n" +
        "INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id\n" +
        "WHERE tb_user.email = :email;")
    List<UsuarioDetailsProjection> searchUserAndRoleByEmail(String email);

    Optional<User> findByEmail(String email);
}
