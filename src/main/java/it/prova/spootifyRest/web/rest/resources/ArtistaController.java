package it.prova.spootifyRest.web.rest.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.spootifyRest.model.Artista;
import it.prova.spootifyRest.service.artista.ArtistaService;
import it.prova.spootifyRest.web.dto.artistaDTO.ArtistaDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;

@RestController
@RequestMapping("/admin/artista")
public class ArtistaController {

	@Autowired
	ArtistaService artistaService;

	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<ArtistaDTO> findById(@PathVariable Long id) {
		Artista artista = artistaService.caricaSingoloArtista(id);
		ArtistaDTO artistaDTO = ArtistaDTO.buildArtistaDTOFromModel(artista, true);
		return ResponseEntity.status(200).body(artistaDTO);

	}

	@PostMapping("/inserisci")
	public ResponseEntity<Message> insert(@Valid @RequestBody ArtistaDTO artistaDTO, BindingResult bindingResult) {
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}

		Artista artista = ArtistaDTO.buildArtistaModelFromDTO(artistaDTO, false);
		artistaService.inserisciNuovo(artista);
		return ResponseEntity.status(200).body(new Message("Inserimento avveuto con successo"));
	}

	@PutMapping("/modifica/{id}")
	public ResponseEntity<Message> modifica(@Valid @RequestBody ArtistaDTO artistaDTO, @PathVariable Long id,
			BindingResult bindingResult) {
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}

		Artista artista = ArtistaDTO.buildArtistaModelFromDTO(artistaDTO, true);
		artista.setId(id);
		artistaService.aggiorna(artista);
		return ResponseEntity.status(200).body(new Message("Modifica avvenuta con successo"));
	}

	@DeleteMapping("/elimina/{id}")
	public ResponseEntity<Message> elimina(@PathVariable Long id) {
		Artista artista = artistaService.caricaSingoloArtista(id);
		artistaService.rimuovi(artista);
		return ResponseEntity.status(200).body(new Message("Cancellazione avvenuta con successo"));
	}
}
