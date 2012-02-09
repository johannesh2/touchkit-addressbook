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

import com.vaadin.addon.touchkit.ui.NavigationView;

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
