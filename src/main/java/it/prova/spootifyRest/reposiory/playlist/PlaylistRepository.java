package it.prova.spootifyRest.reposiory.playlist;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.PlayList;

public interface PlaylistRepository extends CrudRepository<PlayList, Long>, QueryByExampleExecutor<PlayList> {

	@Query("select distinct p from PlayList p left join fetch p.brani b  where p.id= ?1")
	PlayList findByIdEager(Long id);
	
	@Query("select distinct p from PlayList p join p.utente u where u.id = ?1")
	List<PlayList> findAllPlaylistByIdUtente(Long id);
}
