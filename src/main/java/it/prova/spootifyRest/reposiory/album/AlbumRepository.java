package it.prova.spootifyRest.reposiory.album;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.Album;


public interface AlbumRepository extends CrudRepository<Album, Long>, QueryByExampleExecutor<Album> {

	@Query("select distinct a from Album a left join fetch a.brani left join fetch a.artista ar where a.id = ?1")
	Album findByIdEagerArtistaBrani(Long id);
}
