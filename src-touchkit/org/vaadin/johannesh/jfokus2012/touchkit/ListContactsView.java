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

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;

public class ListContactsView extends NavigationView {

    private static final long serialVersionUID = 1L;
    private ResourceBundle tr;
    private CssLayout layout;

    public ListContactsView(String caption) {
        super(caption);
    }

    @SuppressWarnings("serial")
    @Override
    public void attach() {
        super.attach();
        tr = App.getTr(App.get().getLocale());

        Button addButton = new Button(tr.getString("add"),
                rightComponentClickListener);
        setRightComponent(addButton);
        layout = new CssLayout();
        layout.setSizeFull();
        layout.addStyleName("main-layout");
        final Table table = new Table("", App.getPersonsContainer());
        table.addStyleName("contacts");
        table.setSizeFull();
        table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        table.addGeneratedColumn("fullName", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                if ("fullName".equals(columnId)) {
                    return source.getContainerProperty(itemId, "firstName")
                            + " "
                            + source.getContainerProperty(itemId, "lastName");
                }
                return null;
            }
        });
        table.setVisibleColumns(new String[] { "fullName" });
        table.setSelectable(true);
        table.addListener(new ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (table.getValue() != null) {
                    Long personId = (Long) table.getValue();
                    getNavigationManager().navigateTo(
                            new ShowContactView(
                                    tr.getString("showContactView"), personId));
                }
            }
        });
        layout.addComponent(table);
        setContent(layout);

    }

    private final ClickListener rightComponentClickListener = new ClickListener() {

        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            getNavigationManager().navigateTo(
                    new AddContactView(tr.getString("addContactView")));
        }
    };
}
