package it.prova.spootifyRest.reposiory.sessione;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.Sessione;

public interface SessioneRepository extends CrudRepository<Sessione, Long>, QueryByExampleExecutor<Sessione> {

	@Query("from Sessione s join s.utente u where u.id= ?1")
	Sessione findByIdUtente(Long id);
}
