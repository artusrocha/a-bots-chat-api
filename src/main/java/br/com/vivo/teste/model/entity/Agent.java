package br.com.vivo.teste.model.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_bot")
public class Agent implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="uuid2", strategy="uuid2")
	@GeneratedValue(generator="uuid2") 
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(length=125, nullable=false)
	private String name;
	
	private boolean isbot ;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Agent(UUID id, String name, boolean isbot) {
		super();
		this.id = id;
		this.name = name;
		this.isbot = isbot;
	}
	public Agent() {
		super();
		this.name = "Anonimous";
		this.isbot = false;
	}
	public boolean getIsbot() {
		return isbot;
	}
	public void setIsbot(boolean isbot) {
		this.isbot = isbot;
	}

}
