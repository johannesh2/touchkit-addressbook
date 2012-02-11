package org.vaadin.johannesh.jfokus2012.touchkit;

import com.vaadin.data.Property;
import com.vaadin.ui.CheckBox;

public class Favourite extends CheckBox {

	private static final long serialVersionUID = 1L;

	private static final String FAVOURITE_ON = "favourite-on";
	private static final String FAVOURITE_OFF = "favourite-off";
	
	@Override
	public void valueChange(Property.ValueChangeEvent event) {
		super.valueChange(event);
		updateStyles();
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		super.setPropertyDataSource(newDataSource);
		updateStyles();
	}
	
	private void updateStyles() {
		if (Boolean.TRUE.equals(getValue())) {
			removeStyleName(FAVOURITE_OFF);
			addStyleName(FAVOURITE_ON);
		} else {
			removeStyleName(FAVOURITE_ON);
			addStyleName(FAVOURITE_OFF);
		}
		requestRepaint();
 	}

}
