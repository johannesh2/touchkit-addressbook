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

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

public class ListGroupsView extends NavigationView {

    private static final long serialVersionUID = 1L;

    private CssLayout layout;

    public ListGroupsView(String caption) {
        super(caption);
    }

    @Override
    public void attach() {
        super.attach();

        layout = new CssLayout();
        layout.setWidth("100%");
        setContent(layout);

        Button refreshButton = new Button("refresh", leftComponentClickListener);
        setLeftComponent(refreshButton);

        buildGroups();
    }

    private void buildGroups() {
        VerticalComponentGroup allGroup = new VerticalComponentGroup();
        NavigationButton allGroupButton = new NavigationButton("All contacts",
                getNavigationManager().getNextComponent());
        // allGroupButton.addListener(allGroupsClickListener);
        allGroup.addComponent(allGroupButton);
        layout.addComponentAsFirst(allGroup);
    }

    private final ClickListener allGroupsClickListener = new ClickListener() {
        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            getNavigationManager().navigateTo(
                    getNavigationManager().getNextComponent());
        }
    };

    private final ClickListener leftComponentClickListener = new ClickListener() {

        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            App.getPersonsContainer().refresh();
            App.getCompaniesContainer().refresh();
        }

    };
}
