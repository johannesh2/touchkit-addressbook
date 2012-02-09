package org.vaadin.johannesh.jfokus2012.rest;

import org.restlet.util.Resolver;

public class UserResolver extends Resolver<char[]> {

    /**
     * Returns the value that corresponds to the given name.
     */
    @Override
    public char[] resolve(String name) {
        // Could have a look into a database, LDAP directory, etc.
        if ("login".equals(name)) {
            return "secret".toCharArray();
        }

        return null;
    }

}
