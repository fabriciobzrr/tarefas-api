package com.fabriciodev.tarefasapi.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fabriciodev.tarefasapi.entities.Role;
import com.fabriciodev.tarefasapi.entities.User;
import com.fabriciodev.tarefasapi.projections.UsuarioDetailsProjection;
import com.fabriciodev.tarefasapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserService implements UserDetailsService {

	private final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UsuarioDetailsProjection> result = repository.searchUserAndRoleByEmail(username);
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}

		User usuario = new User();
		usuario.setEmail(username);
		usuario.setPassword(result.get(0).getPassword());

		for (UsuarioDetailsProjection projection : result) {
			usuario.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		
		return usuario;
	}
}