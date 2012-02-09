/*
 * Copyright 2012 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

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
