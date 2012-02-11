package org.vaadin.johannesh.jfokus2012.touchkit;

import org.vaadin.johannesh.jfokus2012.domain.AbstractEntity;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;

final class ContactUtils {

    static String formatName(Item item) {
        return String.format("%s %s",
                item.getItemProperty("firstName").toString(),
                item.getItemProperty("lastName")).trim();
    }

    static String formatFieldCaption(Object propertyId) {
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

    static class ContactItemWrapper<T extends AbstractEntity> {

        private final EntityItem<T> entityItem;
        private final BeanItem<T> beanItem;

        public ContactItemWrapper(EntityItem<T> entityItem) {
            this.entityItem = entityItem;
            this.beanItem = null;
        }

        public ContactItemWrapper(BeanItem<T> beanItem) {
            this.entityItem = null;
            this.beanItem = beanItem;
        }

        EntityItem<T> asEntityItem() {
            return entityItem;
        }

        BeanItem<T> asBeanItem() {
            return beanItem;
        }

        T getEntity() {
            return (entityItem == null) ? beanItem.getBean() : entityItem
                    .getEntity();
        }

        Item asItem() {
            return (entityItem == null) ? beanItem : entityItem;
        }
    }
}
