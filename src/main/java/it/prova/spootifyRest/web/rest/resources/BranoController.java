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

import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.service.brano.BranoService;
import it.prova.spootifyRest.web.dto.branoDTO.BranoDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;

@RestController
@RequestMapping("/admin/brano")
public class BranoController {

	@Autowired
	BranoService branoService;

	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<BranoDTO> dettaglio(@PathVariable Long id) {

		Brano brano = branoService.findByIdEagerPlaylistAlbum(id);

		BranoDTO branoDTO = BranoDTO.buildBranoDTOFromModel(brano, false, true, true);
		return ResponseEntity.status(200).body(branoDTO);

	}

	@PostMapping("/inserisci")
	public ResponseEntity<Message> insert(@Valid @RequestBody BranoDTO branoDTO, BindingResult bindingResult) {
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}

		Brano brano = BranoDTO.buildBranoModelFromDTO(branoDTO, false, true, false);
		branoService.inserisciNuovo(brano);

		return ResponseEntity.status(200).body(new Message("Inserimento avvenuto con successo"));
	}

	@PutMapping("/modifica/{id}")
	public ResponseEntity<Message> modifica(@Valid @RequestBody BranoDTO branoDTO, @PathVariable Long id, BindingResult bindingResult) {
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}
		Brano brano = BranoDTO.buildBranoModelFromDTO(branoDTO, false, true, false);
		branoService.aggiorna(brano);

		return ResponseEntity.status(200).body(new Message("Modifica avvenita con successo"));
	}

	@DeleteMapping("/elimina/{id}")
	public ResponseEntity<Message> elimina(@PathVariable Long id) {
		Brano brano = branoService.caricaSingoloBrano(id);
		branoService.rimuovi(brano);
		return ResponseEntity.status(200).body(new Message("Eliminazine avvenuta con successo"));
	}
}
