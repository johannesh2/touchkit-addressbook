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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

public class ListContactsView extends NavigationView {

    private static final long serialVersionUID = 1L;
    private static final String GENERATED_COLUMN_0 = "generatedCol0";
    private Table table;

    private AddEditContactView editView;

    public ListContactsView(String caption) {
        super(caption);
    }

    @SuppressWarnings("serial")
    @Override
    public void attach() {
        super.attach();
        if (getContent() == table) {
            return;
        }

        Button addButton = new Button("add", rightComponentClickListener);
        setRightComponent(addButton);

        table = new Table("", App.getPersonsContainer());
        table.addStyleName("contacts");
        table.setSizeFull();
        table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        table.addGeneratedColumn(GENERATED_COLUMN_0,
                new Table.ColumnGenerator() {
                    @Override
                    public Object generateCell(Table source, Object itemId,
                            Object columnId) {
                        if (GENERATED_COLUMN_0.equals(columnId)) {
                            NavigationButton button = new NavigationButton(
                                    ContactUtils.formatCaption(source
                                            .getItem(itemId)));
                            button.setData(itemId);
                            button.addListener(contactItemClickListener);
                            return button;
                        }
                        return null;
                    }
                });
        table.setVisibleColumns(new String[] { GENERATED_COLUMN_0 });
        setContent(table);

    }

    private final ClickListener rightComponentClickListener = new ClickListener() {

        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            if (editView == null) {
                editView = new AddEditContactView();
            }
            editView.setContactItem(null);
            getNavigationManager().navigateTo(editView);
        }
    };

    private final ClickListener contactItemClickListener = new ClickListener() {

        private static final long serialVersionUID = 1L;

        @Override
        public void buttonClick(ClickEvent event) {
            if (editView == null) {
                editView = new AddEditContactView();
            }
            Object itemId = event.getButton().getData();
            editView.setContactItem(App.getPersonsContainer().getItem(itemId));
            getNavigationManager().navigateTo(editView);
        }

    };
}
