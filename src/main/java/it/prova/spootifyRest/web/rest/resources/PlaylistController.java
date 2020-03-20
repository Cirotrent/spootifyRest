package it.prova.spootifyRest.web.rest.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import it.prova.spootifyRest.model.PlayList;
import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.service.playlist.PlaylistService;
import it.prova.spootifyRest.service.utente.UtenteService;
import it.prova.spootifyRest.web.dto.playlistDTO.PlaylistDTO;
import it.prova.spootifyRest.web.dto.playlistDTO.PlaylistResultDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteDTO;

@RestController
@RequestMapping(value = "/customer/playlist")
public class PlaylistController {

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private PlaylistService playlistService;

	@Autowired
	private HttpServletRequest request;

	@GetMapping("/listAll")
	public ResponseEntity<List<PlaylistResultDTO>> listAll() {
		Utente utenteInSessione = utenteService.findByToken(request.getHeader("token"));
		List<PlayList> playlist = playlistService.findAllPlaylistByIdUtente(utenteInSessione.getId());
		List<PlaylistResultDTO> playlistDTO = PlaylistResultDTO.buildListFromModelLis(playlist);
		return ResponseEntity.status(200).body(playlistDTO);
	}

	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<PlaylistDTO> findById(@PathVariable Long id) {
		PlayList playlist = playlistService.findByIdPlaylistEagerBrani(id);
		PlaylistDTO playlistDTO = PlaylistDTO.buildPlaylistDTOFromModel(playlist, false, false, true);

		return ResponseEntity.status(200).body(playlistDTO);
	}

	@PostMapping("/inserisci")
	public ResponseEntity<Message> insert(@Valid @RequestBody PlaylistDTO playlistDTO,
			BindingResult bindingResult) {
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}

		UtenteDTO utenteInSessioneDTO = UtenteDTO.buildUtenteDTOFromModel(
				utenteService.findByToken(request.getHeader("token")), false, false, false, false);
		playlistDTO.setUtente(utenteInSessioneDTO);
		PlayList playlist = PlaylistDTO.buildPlaylistModelFromDTO(playlistDTO, true, false, true);
		playlistService.inserisciNuova(playlist);

		return ResponseEntity.status(200).body(new Message("Inserimento avvenuto con successo"));
	}

	@PutMapping("/modifica/{id}")
	public ResponseEntity<Message> modifica(@Valid @RequestBody PlaylistDTO playlistDTO, @PathVariable Long id,
			BindingResult bindingResult) {
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}
		
		PlayList playlist = PlaylistDTO.buildPlaylistModelFromDTO(playlistDTO, false, false, false);
		playlist.setId(id);
		playlistService.aggiorna(playlist);

		return ResponseEntity.status(200).body(new Message("Modifica avvenuta con successo"));

	}

	@DeleteMapping("/elimina/{id}")
	public ResponseEntity<Message> elimina(@PathVariable Long id) {
		PlayList playlist = playlistService.caricaSingolaPlayList(id);
		playlistService.rimuovi(playlist);

		return ResponseEntity.status(200)
				.body(new Message("Playlist '" + playlist.getDenominazione() + "' eliminata"));

	}
}
