package it.prova.spootifyRest.reposiory.riproduzione;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.Riproduzione;

public interface RiproduzioneRepository extends CrudRepository<Riproduzione, Long>, QueryByExampleExecutor<Riproduzione>{

	@Query("select distinct r from Riproduzione r join fetch r.album a join r.utente u join fetch r.brano b where a.id = ?1 and u.id = ?2")
	Riproduzione findByIdAlbumEUtente(Long idAlbum, Long idUtente);
	
	@Query("select distinct r from Riproduzione r join fetch r.playList p join r.utente u join fetch r.brano b where p.id = ?1 and u.id = ?2")
	Riproduzione findByIdPlaylisteEUtente(Long idPlaylist, Long idUtente);
	
	
}
