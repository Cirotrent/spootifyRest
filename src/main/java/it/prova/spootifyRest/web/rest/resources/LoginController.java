package it.prova.spootifyRest.web.rest.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.spootifyRest.model.Sessione;
import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.service.sessione.SessioneService;
import it.prova.spootifyRest.service.utente.UtenteService;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteCheAccedeDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteLoggatoDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	UtenteService utenteService;

	@Autowired
	SessioneService sessioneService;

	@PostMapping("/accedi")
	public ResponseEntity<UtenteLoggatoDTO> accedi(@RequestBody UtenteCheAccedeDTO utenteCheAccede) {
		Utente utente = utenteService.eseguiAccesso(utenteCheAccede.getUsername(), utenteCheAccede.getPassword());
		if (utente == null) {
			return ResponseEntity.status(401).body(null);
		}

		Sessione sessione = sessioneService.findByIdUtente(utente.getId());
		
		//utente.setSessione(sessione);
		if (sessione == null) {
			Sessione nuovaSessione=new Sessione();
			nuovaSessione.nuova(utente);
			utente.setSessione(nuovaSessione);
			sessioneService.inserisciNuovo(nuovaSessione);
		} else {
			
			sessione.aggiorna();
			utente.setSessione(sessione);
			sessioneService.aggiorna(sessione);
		}

		utenteService.aggiorna(utente);
		UtenteLoggatoDTO utenteLoggato=UtenteLoggatoDTO.buildUtenteDTOFromModel(utente);
		
		
		return ResponseEntity.status(200).body(utenteLoggato);

	}
	
	@RequestMapping("/error")
	public ResponseEntity<Message> error(){
		return ResponseEntity.status(401).body(new Message("Accesso non autorizzato!"));
		
	}
	@RequestMapping("/error1")
	public ResponseEntity<Message> error1(){
		return ResponseEntity.status(401).body(new Message("Il token inserito non è valido!"));
		
	}
	@RequestMapping("/error2")
	public ResponseEntity<Message> error2(){
		return ResponseEntity.status(401).body(new Message("Album o Playlist inesistente"));
		
	}
	
	@RequestMapping("/scaduta")
	public ResponseEntity<Message> sessioneScaduta(){
		return ResponseEntity.status(408).body(new Message("Sessione scaduta!"));

	}

}
