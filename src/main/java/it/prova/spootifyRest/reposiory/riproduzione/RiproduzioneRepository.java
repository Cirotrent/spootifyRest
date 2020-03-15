package it.prova.spootifyRest.reposiory.riproduzione;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.Riproduzione;

public interface RiproduzioneRepository extends CrudRepository<Riproduzione, Long>, QueryByExampleExecutor<Riproduzione>{

}
