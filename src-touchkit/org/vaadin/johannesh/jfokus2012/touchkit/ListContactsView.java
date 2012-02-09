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

import java.util.ResourceBundle;

import org.vaadin.johannesh.jfokus2012.domain.Company;
import org.vaadin.johannesh.jfokus2012.domain.Person;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.JPAContainerItem;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table.GeneratedRow;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.RowGenerator;

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
		
		Button addButton = new Button(tr.getString("add"), rightComponentClickListener);
		setRightComponent(addButton);
		layout = new CssLayout();
		layout.setSizeFull();
		layout.addStyleName("main-layout");
		final Table table = new Table("", App.getPersonsCachingContainer());
		table.addStyleName("contacts");
		table.setSizeFull();
		table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
		table.addGeneratedColumn("fullName", new Table.ColumnGenerator() {
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				if ("fullName".equals(columnId)) {
					return source.getContainerProperty(itemId, "firstName") + " " + source.getContainerProperty(itemId, "lastName");
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
					Long personId = (Long)table.getValue();
					getNavigationManager().navigateTo(new ShowContactView(tr.getString("showContactView"), personId));
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
			getNavigationManager().navigateTo(new AddContactView(tr.getString("addContactView")));
		}
	};
}
