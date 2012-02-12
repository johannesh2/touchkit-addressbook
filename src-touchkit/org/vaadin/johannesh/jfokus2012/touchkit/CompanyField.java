package org.vaadin.johannesh.jfokus2012.touchkit;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.johannesh.jfokus2012.domain.Company;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Property;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.ListSelect;

/**
 * A custom field that allows selection of a Company.
 */
public class CompanyField extends CustomField {
    private static final long serialVersionUID = 1L;

    private ListSelect companySelect = new ListSelect();

    private JPAContainer<Company> container;

    public CompanyField(String caption) {
        setCaption(caption);
        container = App.getCompaniesContainer();
        container.setApplyFiltersImmediately(false);
        companySelect.setContainerDataSource(container);
        companySelect
                .setItemCaptionPropertyId(ContactUtils.PROPERTY_COMPANY_NAME);

        companySelect.addListener(new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (companySelect.getValue() == null) {
                    setValue(null, false);
                } else {
                    Company entity = container
                            .getItem(companySelect.getValue()).getEntity();
                    setValue(entity, false);
                }
            }
        });

        CssLayout cssLayout = new CssLayout();
        cssLayout.addComponent(companySelect);
        companySelect.setSizeFull();
        setCompositionRoot(cssLayout);
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        super.setPropertyDataSource(newDataSource);
        setCompany(newDataSource.getValue());
    }

    @Override
    public void setValue(Object newValue) throws ReadOnlyException,
            ConversionException {
        setCompany(newValue);
    }

    private void setCompany(Object newValue) {
        Company value = (Company) newValue;
        companySelect.setValue(value != null ? value.getId() : null);
    }

    @Override
    public Class<?> getType() {
        return Company.class;
    }
}
