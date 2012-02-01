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
		persons = JPAContainerFactory.make(Person.class, App.PERSISTENCE_UNIT);
		companies = JPAContainerFactory.make(Company.class, App.PERSISTENCE_UNIT);

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
