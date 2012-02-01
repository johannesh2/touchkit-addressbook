package org.vaadin.johannesh.jfokus2012.touchkit;

import java.util.Locale;
import java.util.ResourceBundle;

import org.vaadin.johannesh.jfokus2012.domain.Company;
import org.vaadin.johannesh.jfokus2012.domain.Person;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.TouchKitApplication;
import com.vaadin.addon.touchkit.ui.TouchKitWindow;

public class App extends TouchKitApplication {
	private static final long serialVersionUID = 1L;
	private TouchKitWindow mainWindow;
	private NavigationManager navigationManager;
	private JPAContainer<Person> persons;
	private JPAContainer<Company> companies;

	public static final String PERSISTENCE_UNIT = "h2";

	@Override
	public void init() {
		configureMainWindow();
		setTheme("jfokus");
	}

	@Override
	public void onBrowserDetailsReady() {
		// TODO Auto-generated method stub
		ListContactsView listView = new ListContactsView(getTr(getLocale()).getString("listContactsView"));
		ListGroupsView listGroups = new ListGroupsView(getTr(getLocale()).getString("listGroupsView"));
		navigationManager = new NavigationManager();
		navigationManager.setCurrentComponent(listView);
		navigationManager.setPreviousComponent(listGroups);
		mainWindow.setContent(navigationManager);
	}
	
	private void configureMainWindow() {
		mainWindow = new TouchKitWindow();
		mainWindow.setCaption("JFokus 2012 - Addressbook Demo");
		mainWindow.addApplicationIcon(getURL() + "../VAADIN/themes/jfokus/icon/icon.png");
		mainWindow.setWebAppCapable(true);
		mainWindow.setPersistentSessionCookie(true);
		setMainWindow(mainWindow);
	}
	
	public static App getApp() {
		return (App)get();
	}
	
	public static ResourceBundle getTr(Locale locale) {
		if (locale == null) {
			locale = Locale.ENGLISH;
		}
		return ResourceBundle.getBundle("CaptionsBundle", locale);
	}
	
	public static JPAContainer<Person> getPersonsCachingContainer() {
		App app = getApp();
		if (app.persons == null) {
			app.persons = new JPAContainer<Person>(Person.class);
			app.persons.setEntityProvider(new CachingLocalEntityProvider<Person>(Person.class, JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT)));
		}
		return app.persons;
	}
	
	public static JPAContainer<Company> getCompaniesCachingContainer() {
		App app = getApp();
		if (app.companies == null) {
			app.companies = new JPAContainer<Company>(Company.class);
			app.companies.setEntityProvider(new CachingLocalEntityProvider<Company>(Company.class, JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT)));
		}
		return app.companies;
	}
}
