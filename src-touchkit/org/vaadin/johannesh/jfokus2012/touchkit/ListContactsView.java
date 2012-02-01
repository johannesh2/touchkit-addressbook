package org.vaadin.johannesh.jfokus2012.touchkit;

import java.util.ResourceBundle;

import org.vaadin.johannesh.jfokus2012.domain.Company;
import org.vaadin.johannesh.jfokus2012.domain.Person;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;

public class ListContactsView extends NavigationView {

	private static final long serialVersionUID = 1L;
	private ResourceBundle tr;
	private JPAContainer<Person> persons;
	private JPAContainer<Company> companies;
	private CssLayout layout;

	public ListContactsView(String caption) {
		super(caption);
	}
	
	@SuppressWarnings("serial")
	@Override
	public void attach() {
		super.attach();
		tr = App.getTr(App.get().getLocale());
		persons = JPAContainerFactory.make(Person.class, App.PERSISTENCE_UNIT);
		companies = JPAContainerFactory.make(Company.class, App.PERSISTENCE_UNIT);
		
		Button addButton = new Button(tr.getString("add"), rightComponentClickListener);
		setRightComponent(addButton);
		layout = new CssLayout();
		layout.setSizeFull();
		layout.addStyleName("main-layout");
		Table table = new Table("", App.getPersonsCachingContainer());
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
