/*
 * Copyright 2012 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.vaadin.johannesh.jfokus2012.touchkit;

import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.Component;

public class ShowContactView extends NavigationView {

	private static final long serialVersionUID = 1L;
	private Long personId;

	public ShowContactView(String caption, Long personId) {
		super(caption);
		this.personId = personId;
	}
	
	@Override
	public void attach() {
		super.attach();
		buildLayout();
	}
	
	private void buildLayout() {
		App.getPersonsCachingContainer().getItem(personId);
	}

}
