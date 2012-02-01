package org.vaadin.johannesh.jfokus2012.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Person extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String firstName;
	
	private String lastName;

	private String email;
	
	private String mobile;
	
	private String pictureUri;
	
	private boolean favourite;
	
	@ManyToOne
	private Company company;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPictureUri() {
		return pictureUri;
	}

	public void setPictureUri(String pictureUri) {
		this.pictureUri = pictureUri;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
