package org.vaadin.johannesh.jfokus2012.rest;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.vaadin.johannesh.jfokus2012.domain.EMF;
import org.vaadin.johannesh.jfokus2012.domain.Person;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class PersonsResource extends ServerResource  {

	@Post("json")
	public String create(String json) {
		Gson gson = new Gson();
		Person person = null;
		ModifyResult result = null;
		try {
			person = gson.fromJson(json, Person.class);
		} catch (JsonSyntaxException e){
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			result = ModifyResult.RESULT_ERROR;
		}
		EntityManager em = EMF.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
			result = new ModifyResult(ModifyResult.SUCCESS, person.getId());
			getResponse().setLocationRef("person/" + person.getId());
			setStatus(Status.SUCCESS_CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			setStatus(Status.SERVER_ERROR_INTERNAL);
			result = ModifyResult.RESULT_ERROR;
		} finally {
			em.close();
		}

		return gson.toJson(result);
	}
	
	@Get
	public String retrieve() {
		Gson gson = new Gson();
		List<Person> persons;
		EntityManager em = EMF.getEntityManagerFactory().createEntityManager();
		
		try {
			TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
			persons = query.getResultList();
		} finally {
			em.close();
		}
		if (persons.isEmpty()) {
			setStatus(Status.SUCCESS_NO_CONTENT);
		} else {
			Type type = new TypeToken<List<Person>>() {}.getType();
			setStatus(Status.SUCCESS_OK);
			return gson.toJson(persons, type);			
		}
		return null;
	}

}
