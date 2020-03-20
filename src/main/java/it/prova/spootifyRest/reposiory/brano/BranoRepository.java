package it.prova.spootifyRest.reposiory.brano;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.Brano;

public interface BranoRepository extends CrudRepository<Brano, Long>, QueryByExampleExecutor<Brano> {
	@Query("select distinct b from Brano b left join fetch b.listaPlaylist p left join fetch b.album a where b.id = ?1")
	Brano findByIdEagerPlaylistAlbum(Long id);
	
	@Query("select distinct b from Brano b join fetch b.album a where a.id = ?1")
	List<Brano> findAllBraniByIdAlbum(Long id);
	
	@Query("select distinct b from Brano b join fetch b.album a join b.listaPlaylist p where p.id = ?1")
	List<Brano> findAllBraniByIdPlaylist(Long id);
}
