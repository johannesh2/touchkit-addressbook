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

import java.util.ResourceBundle;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

public class ListGroupsView extends NavigationView {

    private static final long serialVersionUID = 1L;

    private CssLayout layout;

    private ResourceBundle tr;

    public ListGroupsView(String caption) {
        super(caption);
    }

    @Override
    public void attach() {
        super.attach();

        tr = App.getTr(getLocale());

        layout = new CssLayout();
        layout.setWidth("100%");
        setContent(layout);
        buildGroups();
    }

    private void buildGroups() {
        VerticalComponentGroup allGroup = new VerticalComponentGroup();
        NavigationButton allGroupButton = new NavigationButton(
                tr.getString("group.all"));
        allGroupButton.addListener(rightComponentClickListener);
        allGroup.addComponent(allGroupButton);
        layout.addComponentAsFirst(allGroup);
    }

    private final ClickListener rightComponentClickListener = new ClickListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            getNavigationManager().navigateTo(
                    getNavigationManager().getNextComponent());
        }
    };

    private final ClickListener leftComponentClickListener = new ClickListener() {

        @Override
        public void buttonClick(ClickEvent event) {
            // TODO Auto-generated method stub

        }
    };
}
