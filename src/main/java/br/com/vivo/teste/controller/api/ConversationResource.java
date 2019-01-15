package br.com.vivo.teste.controller.api;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivo.teste.model.entity.Conversation;
import br.com.vivo.teste.model.repository.ConversationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "REST API Conversation")
@RestController
@RequestMapping("/conversation")
public class ConversationResource {

	@Autowired
	private ConversationRepository crud;


	@ApiOperation(value = "Return a Conversation List")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Conversation> list() {
		Iterable<Conversation> list = crud.findAll();
		return list;
	}

	@ApiOperation(value = "Return a Conversation")
	@GetMapping(value = "/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<Conversation> read(@PathVariable(value = "id") UUID id) {
		Conversation conversation = crud.findById(id);
		if (conversation == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(conversation);
	}

	@ApiOperation(value = "Create a Conversation")
	@PostMapping()
	public ResponseEntity<Conversation> create(@RequestBody @Valid Conversation conversation) {
		crud.save(conversation);
		return ResponseEntity.status(HttpStatus.CREATED).body(conversation);
	}

}
