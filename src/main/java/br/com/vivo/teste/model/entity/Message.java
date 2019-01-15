package br.com.vivo.teste.model.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Transient;

@Entity
@Table(name="t_message")
public class Message implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="uuid2", strategy="uuid2")
	@GeneratedValue(generator="uuid2") 
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(length=125, nullable=false)
	private String text;
		
	@OneToOne
	@JoinColumn(nullable=false)
	private Conversation conversation;
		
	@OneToOne
	@JoinColumn(nullable=false)
	private Agent fromAgent;

	@OneToOne
	@JoinColumn(nullable=false)
	private Agent toAgent;
	
	@Transient
	private UUID conversation_id;
	@Transient
	private UUID from;
	@Transient
	private UUID to;

	public UUID getFrom() {
		return from;
	}

	public UUID getTo() {
		return to;
	}

	public Agent getFromAgent() {
		return fromAgent;
	}

	public void setFromAgent(Agent fromAgent) {
		this.fromAgent = fromAgent;
		this.from = fromAgent.getId();
	}

	public Agent getToAgent() {
		return toAgent;
	}

	public void setToAgent(Agent toAgent) {
		this.toAgent = toAgent;
		this.to = toAgent.getId();
	}

	public UUID getConversation_id() {
		return conversation_id;
	}

	public void setConversation_id(UUID conversationId) {
		this.conversation_id = conversationId;
	}

	public void setFrom(UUID from) {
		this.from = from;
	}

	public void setTo(UUID to) {
		this.to = to;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

}
