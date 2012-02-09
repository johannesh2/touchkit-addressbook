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
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Container;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

public class ListGroupsView extends NavigationView {

	private static final long serialVersionUID = 1L;
	
	private CssLayout layout;
	
	private ResourceBundle tr;
	private JPAContainer<Person> persons;
	private JPAContainer<Company> companies;

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
		NavigationButton allGroupButton = new NavigationButton(tr.getString("group.all"));
		allGroupButton.addListener(rightComponentClickListener);
		allGroup.addComponent(allGroupButton);
		layout.addComponentAsFirst(allGroup);
	}
	
	private final ClickListener rightComponentClickListener = new ClickListener() {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			getNavigationManager().navigateTo(getNavigationManager().getNextComponent());
		}
	};
	
	private final ClickListener leftComponentClickListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	};
}
