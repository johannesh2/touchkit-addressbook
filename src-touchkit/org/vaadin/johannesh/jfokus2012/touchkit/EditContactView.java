package org.vaadin.johannesh.jfokus2012.touchkit;

import org.vaadin.addon.formbinder.FormFieldMatch;
import org.vaadin.addon.formbinder.FormView;
import org.vaadin.addon.formbinder.PropertyId;
import org.vaadin.addon.formbinder.ViewBoundForm;
import org.vaadin.johannesh.jfokus2012.entity.Person;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class EditContactView extends NavigationView implements Component {

	@FormView(matchFieldsBy = FormFieldMatch.ANNOTATION)
	public static class MyFormLayout extends CssLayout implements Layout {
		@PropertyId("firstName")
		private TextField first;
		
		@PropertyId("lastName")
		private TextField last;
		
		@PropertyId("email")
		private EmailField email;
		
		public MyFormLayout() {
			first = new TextField("first");
			first.setWidth("100%");
			first.setNullRepresentation("");
			
			last = new TextField("last");
			last.setWidth("100%");
			last.setNullRepresentation("");
			
			email = new EmailField("email");
			email.setWidth("100%");
			email.setNullRepresentation("");
			
			
			VerticalComponentGroup group = new VerticalComponentGroup();
			
			group.addComponent(first);
			group.addComponent(last);
			group.addComponent(email);
			
			addComponent(group);
		}
	}
	
	private EntityItem<Person> item;

	public EditContactView(EntityItem<Person> person) {
		item = person;
	}
	
	@Override
	public void attach() {
		super.attach();
		
		final ViewBoundForm form = new ViewBoundForm(new MyFormLayout());
		form.setItemDataSource(item);
		
		Button saveButton = new Button("save");
		saveButton.addListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				form.commit();
				getNavigationManager().navigateBack();
			}
		});
		
		getNavigationBar().setRightComponent(saveButton);
		
		setContent(form);
		
		
		
	}

}
