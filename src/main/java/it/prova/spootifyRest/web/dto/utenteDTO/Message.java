package it.prova.spootifyRest.web.dto.utenteDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class Message {
	
	private String messaggio="Operazione avvenuta con successo";
	private List<String> errors= new ArrayList<>();
	
	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public Message() {
		super();
	}

	public Message(String messaggio) {
		super();
		this.messaggio = messaggio;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Message(String messaggio, List<FieldError> errors) {
		super();
		this.messaggio = messaggio;
		for(FieldError errorItem:errors) {
			this.errors.add(errorItem.getDefaultMessage());
		}
	}

	public Message(List<String> errors) {
		super();
		this.errors = errors;
	}
	
	

}
