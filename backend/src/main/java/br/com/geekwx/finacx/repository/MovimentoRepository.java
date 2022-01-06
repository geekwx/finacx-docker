package br.com.geekwx.finacx.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geekwx.finacx.models.Movimento;
import br.com.geekwx.finacx.models.Usuario;


/*
 * Repository realizarar toda a comunicação com o banco e efetuara as funções 
 * de salva, atualizar, deletar, visualizar, do banco
 * 
 * */


@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
	
	
	public Page<Movimento> findAllByUsuario(Usuario usuario, Pageable pageable);

}
