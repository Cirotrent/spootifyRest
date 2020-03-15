package it.prova.spootifyRest.web.rest.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.spootifyRest.model.Ruolo;
import it.prova.spootifyRest.model.StatoUtente;
import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.service.utente.UtenteService;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteDTOInsert;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;

@RestController
@RequestMapping(value = "/admin/utente")
public class UtenteController {

	@Autowired
	UtenteService utenteService;

	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<UtenteDTO> findById(@PathVariable Long id) {
		Utente utente = utenteService.findByIdEagerRuoli(id);

		UtenteDTO utenteDTO = UtenteDTO.buildUtenteDTOFromModel(utente, true, false, false, false);

		return ResponseEntity.status(200).body(utenteDTO);
	}

	@PostMapping("/inserisci")
	public ResponseEntity<Message> insert(@Valid @RequestBody UtenteDTOInsert utenteDTO,
			BindingResult bindingResult) {
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}
		Utente utente = UtenteDTOInsert.buildUtenteModelFromDTO(utenteDTO, false, false, false, false);
		utente.getRuoli().add(new Ruolo(2L));
		utenteService.inserisciNuovo(utente);
		return ResponseEntity.status(200).body(new Message("Inserimento avvenuto con successo"));

	}

	@PutMapping("/modifica/{id}")
	public ResponseEntity<Message> modifica(@Valid @RequestBody UtenteDTO utenteDTO, @PathVariable Long id,
			BindingResult bindingResult) {
		
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}
		
		Utente utente = UtenteDTO.buildUtenteModelFromDTO(utenteDTO, true, false, false, false);
		utente.setId(id);
		utenteService.aggiorna(utente);

		return ResponseEntity.status(200).body(new Message("Modifica avvenuta con successo"));
	}

	@PutMapping("/cancella/{id}")
	public ResponseEntity<Message> cancella(@PathVariable Long id) {
		Utente utente = utenteService.caricaSingoloUtente(id);
		utente.setStato(StatoUtente.DISABILITATO);
		utenteService.aggiorna(utente);

		return ResponseEntity.status(200).body(new Message());
	}

}
