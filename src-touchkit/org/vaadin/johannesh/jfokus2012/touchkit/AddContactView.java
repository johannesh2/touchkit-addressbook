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

import javax.persistence.EntityManager;

import org.vaadin.addon.formbinder.ViewBoundForm;
import org.vaadin.johannesh.jfokus2012.domain.Person;

import com.sun.corba.se.impl.orb.AppletDataCollector;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.LocalEntityProvider;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public class AddContactView extends NavigationView {

	private static final long serialVersionUID = 1L;

	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String MOBILE = "mobile";
	private static final String PICTURE_URI = "pictureUri";
	private static final String IS_FAVOURITE = "favourite";

	CssLayout content;
	ViewBoundForm form;
	VerticalComponentGroup group1;
	VerticalComponentGroup group2;
	VerticalComponentGroup group3;
	VerticalComponentGroup group4;
	BeanItem<Person> item;
	ResourceBundle tr;

	public AddContactView(String caption) {
		super(caption);
	}

	@Override
	public void attach() {
		super.attach();
		tr = App.getTr(App.get().getLocale());
		buildView();
	}

	@SuppressWarnings("serial")
	private void buildView() {
		Button undoButton = new Button(tr.getString("undo"));
		undoButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				form.discard();
				getNavigationManager().navigateBack();
			}
		});

		Button readyButton = new Button(tr.getString("ready"));
		readyButton.addStyleName("blue");
		readyButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				form.commit();
				JPAContainer<Person> persons = App.getPersonsCachingContainer();
				EntityManager em = persons.getEntityProvider().getEntityManager();
				try {
					em.getTransaction().begin();
					em.persist(item.getBean());
					em.getTransaction().commit();
					persons.refresh();
				} catch (Exception e) {
					e.printStackTrace();
				}
				getNavigationManager().navigateBack();
			}
		});
		
		form = new ViewBoundForm(new ContactEntityView());
		form.setSizeFull();

		getNavigationBar().setLeftComponent(undoButton);
		getNavigationBar().setRightComponent(readyButton);

		content = new CssLayout();
		content.setWidth("100%");
		item = new BeanItem<Person>(new Person());
		
		form.setItemDataSource(item);
		content.addComponent(form);

		setContent(content);
	}

	static class ContactEntityView extends CssLayout {

		private static final long serialVersionUID = 1L;
		private TextField firstNameField;
		private TextField lastNameField;
		private TextField mobileField;
		private EmailField emailField;
		private Switch favouriteField;
		
		public ContactEntityView() {
			
			setSizeFull();
			ResourceBundle tr = App.getTr(App.getApp().getLocale());
			firstNameField = new TextField(tr.getString("firstName"));
			firstNameField.setWidth("100%");
			firstNameField.setNullRepresentation("");
			lastNameField = new TextField(tr.getString("lastName"));
			lastNameField.setWidth("100%");
			lastNameField.setNullRepresentation("");
			mobileField = new TelField(tr.getString("mobile"));
			mobileField.setWidth("100%");
			mobileField.setNullRepresentation("");
			emailField = new EmailField(tr.getString("email"));
			emailField.setWidth("100%");
			emailField.setNullRepresentation("");
			favouriteField = new Switch(tr.getString("favourite"), false);
			
			VerticalComponentGroup group = new VerticalComponentGroup("");
			group.addComponent(firstNameField);
			group.addComponent(lastNameField);
			addComponent(group);
			group = new VerticalComponentGroup("");
			group.addComponent(mobileField);
			addComponent(group);
			group = new VerticalComponentGroup("");
			group.addComponent(emailField);
			addComponent(group);
			group = new VerticalComponentGroup("");
			group.addComponent(favouriteField);
			addComponent(group);		
		}
		
	}
}
