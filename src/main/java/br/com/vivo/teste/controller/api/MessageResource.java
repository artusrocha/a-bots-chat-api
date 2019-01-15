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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivo.teste.model.entity.Agent;
import br.com.vivo.teste.model.entity.Conversation;
import br.com.vivo.teste.model.entity.Message;
import br.com.vivo.teste.model.repository.AgentRepository;
import br.com.vivo.teste.model.repository.ConversationRepository;
import br.com.vivo.teste.model.repository.MessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "REST API Message")
@RestController
@RequestMapping("/messages")
public class MessageResource {

	@Autowired
	private MessageRepository crud;

	@Autowired
	private ConversationRepository conversationRepo;
	
	@Autowired
	private AgentRepository agentRepo;

	/*
	@ApiOperation(value = "Return a Message List")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Message> list() {
		Iterable<Message> list = crud.findAll();
		return list;
	}
	*/
	
	
	@ApiOperation(value = "Return a Message List From a Conversation")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Message> list(@RequestParam("conversationId") UUID conversationId) {
		Iterable<Message> list = crud.findAllByConversation( conversationRepo.findById( conversationId )); //( conversationId );
		return list; 
	}

	@ApiOperation(value = "Return a Message")
	@GetMapping(value = "/{id}", produces = "application/json")
	public @ResponseBody ResponseEntity<Message> read(@PathVariable(value = "id") UUID id) {
		Message msg = crud.findById(id);
		if (msg == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(msg);
	}

	@ApiOperation(value = "Create a Message")
	@PostMapping()
	public ResponseEntity<Message> create(@RequestBody @Valid Message msg) {
		if (msg.getConversation_id() != null) {
			msg.setConversation( conversationRepo.findById(msg.getConversation_id()));
		} else {
			Conversation c = conversationRepo.save( new Conversation("Nova Conversa") );
			msg.setConversation( c );
		}
		if (msg.getTo() != null) {
			msg.setToAgent( agentRepo.findById(msg.getTo()));
		}
		if (msg.getFrom() != null) {
			msg.setFromAgent( agentRepo.findById(msg.getFrom()));
		} else {
			Agent from = agentRepo.save( new Agent() );
			msg.setFromAgent( from );
		}

		Message message = crud.save(msg);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

}
