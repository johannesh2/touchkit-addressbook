package org.vaadin.johannesh.jfokus2012.touchkit;


import com.vaadin.data.Property;
import com.vaadin.ui.ClientWidget.LoadStyle;
import com.vaadin.ui.TextField;

/**
 * Server side component for the VTelField widget.
 */
@com.vaadin.ui.ClientWidget(value = org.vaadin.johannesh.jfokus2012.touchkit.widgetset.client.ui.VTelField.class, loadStyle = LoadStyle.EAGER)
public class TelField extends TextField {

	private static final long serialVersionUID = 1L;

	public TelField() {
		
	}
	
	public TelField(Property dataSource) {
		super(dataSource);
	}
	
	public TelField(String caption, Property dataSource) {
		super(caption, dataSource);
	}
	
	public TelField(String caption, String value) {
		super(caption, value);
	}
	
	public TelField(String caption) {
		super(caption);
	}
}
