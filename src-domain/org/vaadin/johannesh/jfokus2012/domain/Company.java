package org.vaadin.johannesh.jfokus2012.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Company extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String name;
	
	@OneToMany(mappedBy = "company")
	private Set<Person> persons;
}
