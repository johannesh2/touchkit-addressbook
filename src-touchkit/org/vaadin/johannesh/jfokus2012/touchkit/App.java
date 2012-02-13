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
import org.vaadin.johannesh.jfokus2012.entity.Person;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;
import com.vaadin.addon.touchkit.ui.TouchKitWindow;

public class App extends TouchKitApplication {

	private static final long serialVersionUID = 1L;

	public static final String LABEL_STYLE_STRONG = "strong";
	private static final String APP_ICON = "/app/VAADIN/themes/jfokus/icon/icon.png";
	private static final String APP_ICON_2X = "/app/VAADIN/themes/jfokus/icon/icon@x2.png";

	private TouchKitWindow mainWindow;

	@Override
	public void init() {
		setTheme("jfokus");
		mainWindow = new TouchKitWindow();
		mainWindow.setCaption("Addressbook Demo @ JFokus2012");
		mainWindow.setWebAppCapable(true);
		mainWindow.setPersistentSessionCookie(true);
		mainWindow.addApplicationIcon(57, 57, APP_ICON, false);
		mainWindow.addApplicationIcon(114, 114, APP_ICON_2X, false);
		setMainWindow(mainWindow);
	}

	@Override
	public void onBrowserDetailsReady() {
		NavigationManager navigationManager = new NavigationManager();
		navigationManager.setCurrentComponent(new ContactListView("Contacts"));
		mainWindow.setContent(navigationManager);
	}

	public static App getApp() {
		return (App) get();
	}

	private JPAContainer<Person> persons;

	public static JPAContainer<Person> getPersonsContainer() {
		App app = getApp();
		if (app.persons == null) {
			app.persons = JPAContainerFactory.make(Person.class, EMF
					.getEntityManagerFactory().createEntityManager());
		}
		return app.persons;
	}
}
