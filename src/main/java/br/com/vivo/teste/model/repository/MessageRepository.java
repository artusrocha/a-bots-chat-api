package br.com.vivo.teste.model.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.vivo.teste.model.entity.Conversation;
import br.com.vivo.teste.model.entity.Message;

public interface MessageRepository extends CrudRepository<Message, String>{

	Message findById(UUID id);


	Iterable<Message> findAllByConversation(Conversation findById);

}
