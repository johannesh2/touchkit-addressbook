package org.vaadin.johannesh.jfokus2012.touchkit;

import org.vaadin.johannesh.jfokus2012.entity.Person;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Toolbar;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;

public class ContactListView extends NavigationView implements Component {

	public ContactListView(String caption) {
		super(caption);
	}
	
	@Override
	public void attach() {
		super.attach();
		
		Table table = new Table();
		table.addStyleName("contacts");
		table.setSizeFull();
		
		table.setContainerDataSource(App.getPersonsContainer());
		table.setVisibleColumns(new String[] { "firstName", "lastName" } );
		table.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
		
		
		table.addListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				
				getNavigationManager().navigateTo(new EditContactView((EntityItem<Person>) event.getItem()));
				
			}
		});
		
		setContent(table);
	}

}
