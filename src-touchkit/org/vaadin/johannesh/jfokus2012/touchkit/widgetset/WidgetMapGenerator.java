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

package org.vaadin.johannesh.jfokus2012.touchkit.widgetset;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.touchkit.gwt.TouchKitWidgetMapGenerator;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.addon.touchkit.ui.NavigationBar;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.terminal.Paintable;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class WidgetMapGenerator extends TouchKitWidgetMapGenerator {
    @Override
    protected Collection<Class<? extends Paintable>> getUsedPaintables() {
        ArrayList<Class<? extends Paintable>> a = new ArrayList<Class<? extends Paintable>>();
        a.add(Switch.class);
        a.add(NavigationBar.class);
        a.add(NavigationButton.class);
        a.add(NavigationManager.class);
        a.add(VerticalComponentGroup.class);
        a.add(org.vaadin.johannesh.jfokus2012.touchkit.TelField.class);
        a.add(NavigationView.class);
        a.add(NumberField.class);
        a.add(EmailField.class);
        a.add(TextField.class);
        a.add(Button.class);
        a.add(CssLayout.class);
        a.add(Form.class);

        a.add(Embedded.class);
        a.add(Upload.class);
        a.add(VerticalLayout.class);
        a.add(HorizontalLayout.class);
        a.add(GridLayout.class);
        a.add(AbsoluteLayout.class);
        a.add(TabSheet.class);
        a.add(Table.class);
        a.add(MenuBar.class);
        a.add(Panel.class);
        a.add(Window.class);
        return a;
    }
}
