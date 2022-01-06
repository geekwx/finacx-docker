package br.com.geekwx.finacx.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GeraSenha {

	
	
	

	 static public String cria(String senha) {
		String password = senha;
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		String encodedPassword = passwordEncoder.encode(password);
		return encodedPassword;
	}
}
