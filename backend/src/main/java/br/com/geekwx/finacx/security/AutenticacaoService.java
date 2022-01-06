package br.com.geekwx.finacx.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.geekwx.finacx.models.Usuario;
import br.com.geekwx.finacx.repository.UsuarioRepository;



@Service
public class AutenticacaoService implements UserDetailsService {
	
	
	@Autowired
	private UsuarioRepository repository;
	
	
	public AutenticacaoService(UsuarioRepository repository) {
		this.repository = repository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			Optional<Usuario> usuario = repository.findByUsername(username);
			if (usuario.isPresent()) {
				return (UserDetails) usuario.get();
			}
			
			throw new UsernameNotFoundException("Dados inválidos!");
	}
}
