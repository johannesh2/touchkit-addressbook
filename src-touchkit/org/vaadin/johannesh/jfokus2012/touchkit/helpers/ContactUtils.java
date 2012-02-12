package org.vaadin.johannesh.jfokus2012.touchkit.helpers;


import org.vaadin.johannesh.jfokus2012.entity.Company;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.PropertyFormatter;

public final class ContactUtils {

    public static String formatName(Item item) {
    	Object first = item.getItemProperty(PROPERTY_FIRST_NAME).getValue();
    	Object last = item.getItemProperty(PROPERTY_LAST_NAME).getValue();
        return String.format("%s %s",
                first != null ? first : "",
                last != null ? last : "").trim();
    }

    public static String formatFieldCaption(Object propertyId) {
        String[] split = propertyId.toString().split("[A-Z]");
        if (split == null) {
            return propertyId.toString();
        }
        return split[0];
    }
    
    public static final String PROPERTY_FULLNAME = "fullName";
    public static final String PROPERTY_COMPANY = "company";
    public static final String PROPERTY_FIRST_NAME = "firstName";
    public static final String PROPERTY_LAST_NAME = "lastName";
    public static final String PROPERTY_MOBILE = "mobile";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_FAVOURITE = "favourite";

    public static final String PROPERTY_COMPANY_NAME = "name";

    public static final String[] VISIBLE_CONTACT_PROPERTIES = new String[] {
            PROPERTY_FIRST_NAME, PROPERTY_LAST_NAME, PROPERTY_MOBILE,
            PROPERTY_EMAIL, PROPERTY_FAVOURITE };
    
	public static class CompanyPropertyFormatter extends PropertyFormatter {

		public CompanyPropertyFormatter(Property propertyDataSource) {
			super(propertyDataSource);
		}

		@Override
		public String format(Object value) {
			if (value instanceof Company) {
				return ((Company) value).getName();
			}
			return value.toString();
		}

		@Override
		public Object parse(String formattedValue) throws Exception {
			throw new IllegalArgumentException("Not Supported");
		}
	}
}
