package org.vaadin.johannesh.jfokus2012;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.vaadin.johannesh.jfokus2012.entity.Company;
import org.vaadin.johannesh.jfokus2012.entity.Person;
import org.vaadin.johannesh.jfokus2012.entity.User;

public class UserDataGenerator implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		EntityManager em = EMF.getEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u",
					User.class);
			query.getSingleResult();
		} catch (NoResultException e) {
			// create users
			em.getTransaction().begin();
			User user = new User();
			user.setIdentifier("user");
			user.setSecret("password".toCharArray());
			em.persist(user);

			Company vaadin = new Company();
			vaadin.setName("Vaadin");
			em.persist(vaadin);

			List<Person> persons = new ArrayList<Person>();
			Person p = new Person();
			p.setCompany(vaadin);
			p.setEmail("joonas@vaadin.com");
			p.setFirstName("Joonas");
			p.setLastName("Lehtinen");
			p.setFavourite(true);
			persons.add(p);

			p = new Person();
			p.setCompany(vaadin);
			p.setEmail("fredu@vaadin.com");
			p.setFirstName("Fredrik");
			p.setLastName("Rönnlund");
			persons.add(p);

			p = new Person();
			p.setCompany(vaadin);
			p.setEmail("ville@vaadin.com");
			p.setFirstName("Ville");
			p.setLastName("Ingman");
			persons.add(p);

			p = new Person();
			p.setCompany(vaadin);
			p.setEmail("jani@vaadin.com");
			p.setFirstName("Jani");
			p.setLastName("Laakso");
			persons.add(p);

			for (Person person : persons) {
				em.persist(person);
			}

			vaadin.getPersons().addAll(persons);
			em.merge(vaadin);

			em.getTransaction().commit();
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

}
