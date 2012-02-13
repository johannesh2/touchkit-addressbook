package org.vaadin.johannesh.jfokus2012.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class AddressbookRestApplication extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(this.getContext());

		router.attach("/groups", GroupsResource.class);
		router.attach("/persons", PersonsResource.class);
		router.attach("/persons/{person_id}", OnePersonResource.class);

		return router;
	}

}
