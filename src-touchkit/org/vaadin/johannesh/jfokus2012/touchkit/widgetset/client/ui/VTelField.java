package org.vaadin.johannesh.jfokus2012.touchkit.widgetset.client.ui;

import com.vaadin.terminal.gwt.client.ui.VTextField;

public class VTelField extends VTextField {

	public VTelField() {
		getElement().setPropertyString("type", "tel");
	}

}
