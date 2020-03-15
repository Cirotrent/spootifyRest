package it.prova.spootifyRest.web.rest.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.service.riproduzione.RiproduzioneService;
import it.prova.spootifyRest.web.dto.branoDTO.BranoDTO;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RicercaDTO;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RiproduzioneMessage;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;

@RestController
@RequestMapping("/riproduzione")
public class RiproduzioneController {

	@Autowired
	private RiproduzioneService riproduzioneService;

	@PostMapping("/ricerca")
	public ResponseEntity<Object> ricerca(@Valid @RequestBody RicercaDTO ricercaDTO, BindingResult bindingResult) {
		bindingResult.hasErrors();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (!errors.isEmpty()) {
			return ResponseEntity.status(406).body(new Message("I dati inseriti non sono corretti", errors));
		}
		RiproduzioneMessage message = riproduzioneService.controllo(ricercaDTO);
		if (message != null) {
			return ResponseEntity.status(406).body(message);
		}
		List<Brano> brani = riproduzioneService.findBrani(ricercaDTO);
		if (brani.isEmpty()) {
			return ResponseEntity.status(406).body(new RiproduzioneMessage("Album o playlist inesistenti!"));
		}
		List<BranoDTO> braniDTO = BranoDTO.buildListFromModelList(brani);

		return ResponseEntity.status(200).body(braniDTO);
	}

}
