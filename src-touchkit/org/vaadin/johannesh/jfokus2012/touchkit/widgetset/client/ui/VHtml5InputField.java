/*
 * Demo application for JFokus 2012 presentation: Building iOS Applications in Java
 * Copyright (C) 2012  Vaadin Ltd.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.vaadin.johannesh.jfokus2012.touchkit.widgetset.client.ui;

import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VTextField;

/**
 * Add support for url, email, phone and search HTML5 fields
 * @author johku
 *
 */
public class VHtml5InputField extends VTextField {

    public VHtml5InputField() {
    }
    
    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
    	super.updateFromUIDL(uidl, client);
    	
    	if (client.updateComponent(this, uidl, true)) {
    		return;
    	}
    	
    	if (uidl.hasAttribute("html5-input-type")) {
    		getElement().setPropertyString("type", uidl.getStringAttribute("html5-input-type"));
    	}
    }

}
