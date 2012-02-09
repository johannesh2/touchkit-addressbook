package org.vaadin.johannesh.jfokus2012.rest;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.restlet.security.SecretVerifier;
import org.vaadin.johannesh.jfokus2012.domain.EMF;
import org.vaadin.johannesh.jfokus2012.domain.User;

public class UserSecretVerifier extends SecretVerifier {

    @Override
    public boolean verify(String identifier, char[] secret)
            throws IllegalArgumentException {
        EntityManager em = EMF.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.identifier = ?1", User.class);
            query.setParameter(1, identifier);
            // query.setParameter(2, secret);
            List<User> users = query.getResultList();
            return (users.get(0).getIdentifier().equals(identifier) && Arrays
                    .equals(users.get(0).getSecret(), secret));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

}
