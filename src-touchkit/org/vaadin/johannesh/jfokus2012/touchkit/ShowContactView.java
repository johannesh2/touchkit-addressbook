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

import org.vaadin.johannesh.jfokus2012.domain.Company;
import org.vaadin.johannesh.jfokus2012.domain.Person;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Property;
import com.vaadin.data.util.PropertyFormatter;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class ShowContactView extends NavigationView {

	private EntityItem<Person> item;
	private CssLayout layout;

	public ShowContactView(EntityItem<Person> item) {
		super("");
		this.item = item;
	}

	@Override
	public void attach() {
		super.attach();
		buildLayout();
	}

	private void buildLayout() {
		layout = new CssLayout();
		layout.addStyleName("show-contact-view");
		layout.setWidth("100%");

		VerticalComponentGroup infoGroup = new VerticalComponentGroup("");
		infoGroup.setWidth("100%");

		Component label;
		Property p;

		p = item.getItemProperty(ContactUtils.PROPERTY_COMPANY);
		label = new Label(new CompanyPropertyFormatter(p));
		label.setCaption(ContactUtils
				.formatFieldCaption(ContactUtils.PROPERTY_COMPANY));
		infoGroup.addComponent(label);

		p = item.getItemProperty(ContactUtils.PROPERTY_MOBILE);
		label = new Label(p);
		label.setCaption(ContactUtils
				.formatFieldCaption(ContactUtils.PROPERTY_MOBILE));
		infoGroup.addComponent(label);

		p = item.getItemProperty(ContactUtils.PROPERTY_EMAIL);
		label = new Label(p);
		label.setCaption(ContactUtils
				.formatFieldCaption(ContactUtils.PROPERTY_EMAIL));
		infoGroup.addComponent(label);

		Embedded picture = new Embedded("", new ThemeResource(
				"icon/picture.png"));
		picture.setWidth("57px");
		picture.setHeight("57px");

		Label firstName = new Label(
				item.getItemProperty(ContactUtils.PROPERTY_FIRST_NAME));
		firstName.addStyleName("strong-name");

		Label lastName = new Label(
				item.getItemProperty(ContactUtils.PROPERTY_LAST_NAME));
		lastName.addStyleName("strong-name");

		GridLayout nameLayout = new GridLayout(2, 2);
		nameLayout.setWidth("100%");
		nameLayout.setSpacing(true);
		nameLayout.setMargin(true, true, false, true);
		nameLayout.setColumnExpandRatio(1, 1.0f);
		nameLayout.addComponent(picture, 0, 0, 0, 1);
		nameLayout.addComponent(firstName, 1, 0);
		nameLayout.addComponent(lastName, 1, 1);
		nameLayout.setComponentAlignment(firstName, Alignment.MIDDLE_LEFT);
		nameLayout.setComponentAlignment(lastName, Alignment.MIDDLE_LEFT);

		final Favourite favourite = new Favourite();
		favourite.setImmediate(true);
		favourite.setReadOnly(true);
		favourite.setIcon(new ThemeResource("icon/favourite.png"));
		favourite.setPropertyDataSource(item
				.getItemProperty(ContactUtils.PROPERTY_FAVOURITE));

		layout.addComponent(nameLayout);
		layout.addComponent(favourite);
		layout.addComponent(infoGroup);

		Button editButton = new Button("Edit");
		editButton.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getNavigationManager().navigateTo(new EditContactView(item));
			}
		});
		setRightComponent(editButton);
		setContent(layout);
	}

	private static class CompanyPropertyFormatter extends PropertyFormatter {

		public CompanyPropertyFormatter(Property propertyDataSource) {
			super(propertyDataSource);
		}

		@Override
		public String format(Object value) {
			if (value instanceof Company) {
				return ((Company) value).getName();
			}
			return value.toString();
		}

		@Override
		public Object parse(String formattedValue) throws Exception {
			throw new IllegalArgumentException("Not Supported");
		}
	}
}
