package it.prova.spootifyRest.reposiory.utente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.prova.spootifyRest.model.StatoUtente;
import it.prova.spootifyRest.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, QueryByExampleExecutor<Utente> {

	@Query("select distinct u from Utente u join fetch u.ruoli r where u.password= ?1 and u.username=?2 and u.stato = ?3")
	Utente findByUsernameAndPasswordAndStato(String username, String password, StatoUtente stato);

	@Query("select distinct u from Utente u join fetch u.ruoli r where u.id= ?1")
	Utente findByIdEagerRuoli(Long id);

	@Query("select distinct u from Utente u join fetch u.ruoli r join fetch u.sessione s where s.token = ?1")
	Utente findByToken(String token);
}
