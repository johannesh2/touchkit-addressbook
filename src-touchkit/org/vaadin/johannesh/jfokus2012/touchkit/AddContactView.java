package org.vaadin.johannesh.jfokus2012.touchkit;

import java.util.ResourceBundle;

import org.vaadin.johannesh.jfokus2012.domain.Person;

import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class AddContactView extends NavigationView {

	private static final long serialVersionUID = 1L;
	
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String MOBILE = "mobile";
	private static final String PICTURE_URI = "pictureUri";
	private static final String IS_FAVOURITE = "favourite";
	
	CssLayout content;
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
				getNavigationManager().navigateBack();
			}
		});
		
		Button readyButton = new Button(tr.getString("ready"));
		readyButton.addStyleName("blue");
		readyButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getNavigationManager().navigateBack();
			}
		});
		
		getNavigationBar().setLeftComponent(undoButton);
		getNavigationBar().setRightComponent(readyButton);
		
		content = new CssLayout();
		content.setWidth("100%");
		item = new BeanItem<Person>(new Person());
		
		group1 = new VerticalComponentGroup("");
		content.addComponent(group1);
		group2 = new VerticalComponentGroup("");
		content.addComponent(group2);
		group3 = new VerticalComponentGroup("");
		content.addComponent(group3);
		group4 = new VerticalComponentGroup("");
		content.addComponent(group4);
		
		addFields();
		
		setContent(content);
	}

	private void addFields() {
		TextField first = new TextField(tr.getString("firstName"));
		addField(group1, item, first, FIRST_NAME);
		TextField last = new TextField(tr.getString("lastName"));
		addField(group1, item, last, LAST_NAME);
		TextField mobile = new TelField(tr.getString("mobile"));
		addField(group2, item, mobile, MOBILE);
		EmailField email = new EmailField(tr.getString("email"));
		addField(group3, item, email, EMAIL);
		Switch favourite = new Switch(tr.getString("favourite"), false);
		addField(group4, item, favourite, IS_FAVOURITE);
	}
	
	private void addField(VerticalComponentGroup group, Item item, Field field, String property) {
		if (field instanceof TextField) {
			((TextField)field).setNullRepresentation("");
		}
		if (!(field instanceof Switch)) {
			field.setWidth("100%");
		}
		field.setCaption(tr.getString(property));
		field.setPropertyDataSource(item.getItemProperty(property));
		group.addComponent(field);
	}
}
