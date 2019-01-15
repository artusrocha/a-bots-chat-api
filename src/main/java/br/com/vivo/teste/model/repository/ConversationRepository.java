package br.com.vivo.teste.model.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.vivo.teste.model.entity.Conversation;

public interface ConversationRepository extends CrudRepository<Conversation, String>{

	Conversation findById(UUID id);

}
