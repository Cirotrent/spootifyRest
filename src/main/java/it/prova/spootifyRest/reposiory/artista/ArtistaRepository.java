package it.prova.spootifyRest.reposiory.artista;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.Artista;

public interface ArtistaRepository extends CrudRepository<Artista, Long>, QueryByExampleExecutor<Artista> {

	@Query("select distinct a from Artista a left join fetch a.listaAlbum la where a.id = ?1")
	Artista findByIdEagerAlbum(Long id);
}
