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
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.ClientWidget.LoadStyle;
import com.vaadin.ui.TextField;

/**
 * Server side component for the VTelField widget.
 */
@com.vaadin.ui.ClientWidget(value = org.vaadin.johannesh.jfokus2012.touchkit.widgetset.client.ui.VHtml5InputField.class, loadStyle = LoadStyle.EAGER)
public class Html5InputField extends TextField {

    private static final long serialVersionUID = 1L;

    public enum InputType {
        Text("text"), Number("number"), Tel("tel"), Url("url"), Email("email");

        private InputType(String type) {
            this.type = type;
        }

        public final String type;

    }

    private InputType inputType = InputType.Text;

    public Html5InputField() {

    }

    public Html5InputField(Property dataSource) {
        super(dataSource);
    }

    public Html5InputField(String caption, Property dataSource) {
        super(caption, dataSource);
    }

    public Html5InputField(String caption, String value) {
        super(caption, value);
    }

    public Html5InputField(String caption) {
        this(caption, InputType.Text);
    }

    public Html5InputField(String caption, InputType type) {
        super(caption);
        this.inputType = type;
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        target.addAttribute("html5-input-type", inputType.type);
    }

    public void setInputType(InputType inputType) {
        this.inputType = inputType;
        requestRepaint();
    }
}
