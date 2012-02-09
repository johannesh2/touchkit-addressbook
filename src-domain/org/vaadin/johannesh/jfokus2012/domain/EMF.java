package org.vaadin.johannesh.jfokus2012.domain;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EMF implements ServletContextListener {
	
	private static EntityManagerFactory emf;
	public static final String PERSISTENCE_UNIT = "h2";

	@Override
	public void contextInitialized(ServletContextEvent event) {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		emf.close();
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
}
