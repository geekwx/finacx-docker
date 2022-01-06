package br.com.geekwx.finacx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geekwx.finacx.models.Categoria;
import br.com.geekwx.finacx.models.Usuario;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	
	public List<Categoria> findAllByUsuario(Usuario usuario);
}
