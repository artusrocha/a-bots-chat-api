package br.com.vivo.teste.controller.api;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivo.teste.model.entity.Agent;
import br.com.vivo.teste.model.repository.AgentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "REST API Bot")
@RestController
@RequestMapping("/bots")
public class BotResource {

	@Autowired
	private AgentRepository repository;
	
	@ApiOperation(value="Return a Bot List")
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Agent> list(){
		Iterable<Agent> list = repository.findAll();
		return list;
	}
	
	@ApiOperation(value="Return a Bot")
	@GetMapping(value="/{id}", produces="application/json")
	public @ResponseBody ResponseEntity<Agent> read(@PathVariable(value="id") UUID id){
		Agent bot = repository.findById(id);
		if( bot == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return  ResponseEntity.ok( bot );
	}
	
	@ApiOperation(value="Create a Bot")
	@PostMapping()
	public ResponseEntity<Agent> create(@RequestBody @Valid Agent agent){
		Agent a = repository.save(agent);
		return ResponseEntity.status(HttpStatus.CREATED).body( a );
	}

	@ApiOperation(value="Update a Bot")
	@PutMapping(value="/{id}")
	public ResponseEntity<Agent> update(
			@PathVariable(value="id") UUID id,
			@RequestBody @Valid Agent bot){
		if( repository.findById(bot.getId()) == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		repository.save(bot);
		return ResponseEntity.status(HttpStatus.CREATED).body( bot );
	}

	@ApiOperation(value="Delete a Bot")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Agent> delete(@PathVariable(value="id") UUID id){
		Agent bot = repository.findById(id);
		if( bot == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		repository.delete(bot);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

}
