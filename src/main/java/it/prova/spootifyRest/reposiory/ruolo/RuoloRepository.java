package it.prova.spootifyRest.reposiory.ruolo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long>, QueryByExampleExecutor<Ruolo> {

}
