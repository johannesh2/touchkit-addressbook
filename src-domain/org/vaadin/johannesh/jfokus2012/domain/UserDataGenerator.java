package org.vaadin.johannesh.jfokus2012.domain;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserDataGenerator implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		EntityManager em = EMF.getEntityManagerFactory().createEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
			query.getSingleResult();
		} catch (NoResultException e) {
			// create users
			em.getTransaction().begin();
			User user = new User();
			user.setIdentifier("user");
			user.setSecret("password".toCharArray());
			em.persist(user);
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
