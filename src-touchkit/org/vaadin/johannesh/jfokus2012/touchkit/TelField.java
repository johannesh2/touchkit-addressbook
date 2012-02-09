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
