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

import org.vaadin.addon.formbinder.ViewBoundForm;
import org.vaadin.johannesh.jfokus2012.domain.Person;
import org.vaadin.johannesh.jfokus2012.touchkit.ContactUtils.ContactItemWrapper;
import org.vaadin.johannesh.jfokus2012.touchkit.Html5InputField.InputType;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class EditContactView extends NavigationView {

	public static final String DEFAULT_CAPTION = "Add a contact";

	private CssLayout content;
	private ViewBoundForm form;
	private final ContactItemWrapper<Person> editedItem;

	public EditContactView() {
		this(null);
	}

	public EditContactView(EntityItem<Person> item) {
		if (item == null) {
			editedItem = new ContactItemWrapper<Person>(new BeanItem<Person>(
					new Person()));
		} else {
			editedItem = new ContactItemWrapper<Person>(item);
		}
	}

	@Override
	public void attach() {
		super.attach();
		buildView();
		if (!(editedItem instanceof EntityItem)) {
			setCaption(DEFAULT_CAPTION);
		}
	}

	private void buildView() {
		Button undoButton = new Button("Undo");
		undoButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				form.discard();
				getNavigationManager().navigateBack();
			}
		});

		Button readyButton = new Button("Ready");
		readyButton.addStyleName("blue");
		readyButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				form.commit();
				JPAContainer<Person> persons = App.getPersonsContainer();
				persons.addEntity(editedItem.getEntity());
				getNavigationManager().navigateBack();
			}
		});

		form = new ViewBoundForm(new ContactEntityView());
		form.getLayout().setWidth("100%");
		form.setSizeUndefined();
		form.setWidth("100%");

		getNavigationBar().setLeftComponent(undoButton);
		getNavigationBar().setRightComponent(readyButton);

		content = new CssLayout();
		content.setSizeUndefined();
		content.setWidth("100%");

		form.setItemDataSource(editedItem.asItem());
		content.addComponent(form);

		setContent(content);
	}

	static class ContactEntityView extends CssLayout {

		private static final long serialVersionUID = 1L;
		private TextField firstNameField;
		private TextField lastNameField;
		private CompanyField companyField;
		private Html5InputField mobileField;
		private Html5InputField emailField;
		private Switch favouriteField;

		public ContactEntityView() {

			firstNameField = new TextField("first");
			firstNameField.setWidth("100%");
			firstNameField.setNullRepresentation("");
			lastNameField = new TextField("last");
			lastNameField.setWidth("100%");
			lastNameField.setNullRepresentation("");
			companyField = new CompanyField("company");
			companyField.setWidth("100%");

			mobileField = new Html5InputField("mobile", InputType.Tel);
			mobileField.setWidth("100%");
			mobileField.setNullRepresentation("");

			emailField = new Html5InputField("email", InputType.Email);
			emailField.setWidth("100%");
			emailField.setNullRepresentation("");

			favouriteField = new Switch("favourite", false);

			VerticalComponentGroup group = new VerticalComponentGroup("");
			group.addComponent(firstNameField);
			group.addComponent(lastNameField);
			group.addComponent(companyField);
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
