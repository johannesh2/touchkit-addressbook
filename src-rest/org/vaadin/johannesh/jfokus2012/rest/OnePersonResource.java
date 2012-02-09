package org.vaadin.johannesh.jfokus2012.rest;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.vaadin.johannesh.jfokus2012.domain.EMF;
import org.vaadin.johannesh.jfokus2012.domain.Person;

import com.google.gson.Gson;

public class OnePersonResource extends ServerResource {

    @Get
    public Representation getJson() {
        EntityManager em = EMF.getEntityManagerFactory().createEntityManager();
        Long id = 1L;
        String json = "{ }";
        try {
            Person person = em.find(Person.class, id);
            Gson gson = new Gson();
            json = gson.toJson(person);
        } finally {
            em.close();
        }
        getResponse().setStatus(Status.SUCCESS_OK);
        return new StringRepresentation(json, MediaType.APPLICATION_JSON);
    }

    @Put("json")
    public void update(String json) {
        EntityManager em = EMF.getEntityManagerFactory().createEntityManager();
        try {
            Long person_id = Long.parseLong((String) getRequestAttributes()
                    .get("person_id"));
            em.getTransaction().begin();
            TypedQuery<Person> query = em.createQuery(
                    "SELECT p FROM Person p WHERE p.id = :person_id",
                    Person.class);
            query.setParameter("person_id", person_id);
            Person person = query.getSingleResult();
            if (person != null) {
                Gson gson = new Gson();
                person = copy(gson.fromJson(json, Person.class), person);
                em.merge(person);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (NumberFormatException e) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
        } catch (NoResultException e) {
            setStatus(Status.CLIENT_ERROR_NOT_FOUND);
        } catch (Exception e) {
            setStatus(Status.SERVER_ERROR_INTERNAL);
        } finally {
            em.close();
        }
        setStatus(Status.SUCCESS_NO_CONTENT);
    }

    private static Person copy(Person from, Person to) {
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setEmail(from.getEmail());
        to.setMobile(from.getMobile());
        to.setPictureUri(from.getPictureUri());
        to.setFavourite(from.isFavourite());
        return to;
    }

    // private static Company copy(Company from, Company to) {
    // return to;
    // }
}
