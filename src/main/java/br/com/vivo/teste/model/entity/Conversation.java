package br.com.vivo.teste.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_conversation")
public class Conversation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="uuid2", strategy="uuid2")
	@GeneratedValue(generator="uuid2") 
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(length=125, nullable=false)
	private String topic;
	
/*	@OneToMany(mappedBy="conversation")
	private List<Message> msgs ;*/
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public Conversation(UUID id, String topic) {
		super();
		this.id = id;
		this.topic = topic;
	}
	public Conversation(String topic) {
		super();
		this.topic = topic;
	}
	public Conversation() {
		super();
	}

}
