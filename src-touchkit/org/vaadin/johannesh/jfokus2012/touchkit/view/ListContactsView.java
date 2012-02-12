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

package org.vaadin.johannesh.jfokus2012.touchkit.view;

import org.vaadin.johannesh.jfokus2012.entity.Person;
import org.vaadin.johannesh.jfokus2012.touchkit.App;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class ListContactsView extends NavigationView {

	public static final String DEFAULT_CAPTION = "Contacts";

	private Table table;
	private Long groupId;

	public ListContactsView() {
		this(null);
	}

	public ListContactsView(Long groupId) {
		this.groupId = groupId;
	}

	@Override
	public void attach() {
		super.attach();
		if (getContent() == table) {
			return;
		}

		if (groupId == null) {
			setCaption(DEFAULT_CAPTION);
		} else {
			// Fetch by filter
		}

		Button addButton = new Button("Add", addButtonClickListener);
		setRightComponent(addButton);

		App.getPersonsContainer().addNestedContainerProperty("company.name");
		table = new Table("", App.getPersonsContainer());
		table.addStyleName("contacts");
		table.setSizeFull();
		table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);

		table.addListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				getNavigationManager().navigateTo(new ShowContactView((EntityItem<Person>) event.getItem()));
			}
		});
		table.setVisibleColumns(new Object[] { "firstName", "lastName", "company.name" } );
		table.setColumnExpandRatio("company.name", 1.0f);
		table.setColumnAlignment("company.name", Table.ALIGN_RIGHT);
		setContent(table);

	}

	private final ClickListener addButtonClickListener = new ClickListener() {

		@Override
		public void buttonClick(ClickEvent event) {
			getNavigationManager().navigateTo(new EditContactView());
		}

	};
}
