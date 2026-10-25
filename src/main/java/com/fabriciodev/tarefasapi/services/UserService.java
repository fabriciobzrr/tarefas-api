package com.fabriciodev.tarefasapi.services;

import java.util.List;

import com.fabriciodev.tarefasapi.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.fabriciodev.tarefasapi.entities.Role;
import com.fabriciodev.tarefasapi.entities.User;
import com.fabriciodev.tarefasapi.projections.UsuarioDetailsProjection;
import com.fabriciodev.tarefasapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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

		User user = new User();
		user.setEmail(username);
		user.setPassword(result.get(0).getPassword());

		for (UsuarioDetailsProjection projection : result) {
			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		
		return user;
	}

	protected User authenticated() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) auth.getPrincipal();
			String username = jwtPrincipal.getClaim("username");

			return repository.findByEmail(username).get();
		} catch (Exception err) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
	}

	@Transactional(readOnly = true)
	public UserDto findMe() {
		User user = authenticated();
		return new UserDto(user);
	}
}