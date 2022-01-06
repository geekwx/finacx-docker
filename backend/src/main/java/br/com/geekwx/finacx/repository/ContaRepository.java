package br.com.geekwx.finacx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.geekwx.finacx.models.Conta;
import br.com.geekwx.finacx.models.Usuario;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	public List<Conta> findAllByUsuario(Usuario usuario);

}
