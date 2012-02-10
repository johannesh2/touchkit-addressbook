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

import org.vaadin.addon.formbinder.ViewBoundForm;
import org.vaadin.johannesh.jfokus2012.domain.Person;
import org.vaadin.johannesh.jfokus2012.touchkit.Html5InputField.InputType;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

public class AddContactView extends NavigationView {

    private static final long serialVersionUID = 1L;

    // private static final String FIRST_NAME = "firstName";
    // private static final String LAST_NAME = "lastName";
    // private static final String EMAIL = "email";
    // private static final String MOBILE = "mobile";
    // private static final String PICTURE_URI = "pictureUri";
    // private static final String IS_FAVOURITE = "favourite";

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
                JPAContainer<Person> persons = App.getPersonsContainer();
                persons.addEntity(item.getBean());
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
        private AbstractSelect companyField;
        private Html5InputField mobileField;
        private Html5InputField emailField;
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
            companyField = new ListSelect(tr.getString("company"));
            companyField.setContainerDataSource(App.getCompaniesContainer());
            companyField.setWidth("100%");
            companyField.setNullSelectionAllowed(true);
            companyField.setNullSelectionItemId("null-selection");
            companyField.setItemCaption("null-selection", tr.getString("noCompany"));
            companyField.setContainerDataSource(App.getCompaniesContainer());

            mobileField = new Html5InputField(tr.getString("mobile"));
            mobileField.setInputType(InputType.Tel);
            mobileField.setWidth("100%");
            mobileField.setNullRepresentation("");
            
            emailField = new Html5InputField(tr.getString("email"));
            emailField.setInputType(InputType.Email);
            emailField.setWidth("100%");
            emailField.setNullRepresentation("");
            
            favouriteField = new Switch(tr.getString("favourite"), false);

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
