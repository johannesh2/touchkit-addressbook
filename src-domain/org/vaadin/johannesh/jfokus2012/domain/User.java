package org.vaadin.johannesh.jfokus2012.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String identifier;
	
	@NotNull
	@Size(min = 6)
	private char[] secret;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public char[] getSecret() {
		return secret;
	}

	public void setSecret(char[] secret) {
		this.secret = secret;
	}

}
