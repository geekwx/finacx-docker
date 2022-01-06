package br.com.geekwx.finacx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.geekwx.finacx.models.Permissao;


/*
 * Interface para pegar as referencia das permissao no banco
 * */
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
