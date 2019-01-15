package br.com.vivo.teste.model.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.vivo.teste.model.entity.Agent;

public interface AgentRepository extends CrudRepository<Agent, String>{

	Agent findById(UUID id);


}
