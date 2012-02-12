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

import org.vaadin.johannesh.jfokus2012.EMF;
import org.vaadin.johannesh.jfokus2012.entity.Company;
import org.vaadin.johannesh.jfokus2012.entity.Person;
import org.vaadin.johannesh.jfokus2012.touchkit.view.ListContactsView;
import org.vaadin.johannesh.jfokus2012.touchkit.view.ListGroupsView;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;
import com.vaadin.addon.touchkit.ui.TouchKitWindow;

public class App extends TouchKitApplication {
    private static final long serialVersionUID = 1L;
    
    private TouchKitWindow mainWindow;
    private NavigationManager navigationManager;
    private JPAContainer<Person> persons;
    private JPAContainer<Company> companies;

    @Override
    public void init() {
        configureMainWindow();
        setTheme("jfokus");
    }

    private void configureMainWindow() {
        mainWindow = new TouchKitWindow();
        mainWindow.setCaption("JFokus 2012 - Addressbook Demo");
        mainWindow.addApplicationIcon(57, 57, "/app/VAADIN/themes/jfokus/icon/icon.png", false);
        mainWindow.addApplicationIcon(114, 114, "/app/VAADIN/themes/jfokus/icon/icon@2x.png", false);
        mainWindow.setWebAppCapable(true);
        mainWindow.setPersistentSessionCookie(true);
        setMainWindow(mainWindow);
    }

    @Override
    public void onBrowserDetailsReady() {
        ListContactsView listView = new ListContactsView();
        ListGroupsView listGroups = new ListGroupsView();
        navigationManager = new NavigationManager();
        navigationManager.setCurrentComponent(listView);
        navigationManager.setPreviousComponent(listGroups);
        mainWindow.setContent(navigationManager);
    }

    public static App getApp() {
        return (App) get();
    }

    public static JPAContainer<Person> getPersonsContainer() {
        App app = getApp();
        if (app.persons == null) {
            app.persons = JPAContainerFactory.make(Person.class, EMF
                    .getEntityManagerFactory().createEntityManager());
        }
        return app.persons;
    }

    public static JPAContainer<Company> getCompaniesContainer() {
        App app = getApp();
        if (app.companies == null) {
            app.companies = JPAContainerFactory.make(Company.class, EMF
                    .getEntityManagerFactory().createEntityManager());
        }
        return app.companies;
    }
}
