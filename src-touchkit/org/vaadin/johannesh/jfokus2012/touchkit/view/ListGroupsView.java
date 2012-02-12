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

package org.vaadin.johannesh.jfokus2012.touchkit.view;

import org.vaadin.johannesh.jfokus2012.touchkit.App;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;

@SuppressWarnings("serial")
public class ListGroupsView extends NavigationView {

	public static final String DEFAULT_CAPTION = "Groups";

	private CssLayout layout;

	public ListGroupsView() {
	}

	@Override
	public void attach() {
		super.attach();
		if (getContent() == layout) {
			return;
		}

		layout = new CssLayout();
		layout.setWidth("100%");
		setContent(layout);

		Button refreshButton = new Button("Refresh", leftComponentClickListener);
		setCaption(DEFAULT_CAPTION);
		setLeftComponent(refreshButton);

		buildGroups();
	}

	private void buildGroups() {
		VerticalComponentGroup allGroup = new VerticalComponentGroup();
		NavigationButton allGroupButton = new NavigationButton(
				ListContactsView.DEFAULT_CAPTION, new ListContactsView());
		allGroup.addComponent(allGroupButton);
		layout.addComponentAsFirst(allGroup);
	}

	private final ClickListener leftComponentClickListener = new ClickListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			App.getPersonsContainer().refresh();
			App.getCompaniesContainer().refresh();
		}

	};
}
