package org.vaadin.johannesh.jfokus2012.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;

public class AddressbookRestApplication extends Application {
	
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(this.getContext());
		
		ChallengeAuthenticator authenticator = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_BASIC, "jfokus2012");
		authenticator.setVerifier(new UserSecretVerifier());
		router.attach("/groups", authenticator);
		authenticator.setNext(GroupsResource.class);
		router.attach("/persons", authenticator);
		authenticator.setNext(PersonsResource.class);
		router.attach("/persons/{person_id}", authenticator);
		authenticator.setNext(OnePersonResource.class);
		return router;
	}
	
	
}
