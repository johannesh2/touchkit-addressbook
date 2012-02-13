package org.vaadin.johannesh.jfokus2012.entity;


import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;

public class ItemWrapper<T extends AbstractEntity> {

    private final EntityItem<T> entityItem;
    private final BeanItem<T> beanItem;

    public ItemWrapper(EntityItem<T> entityItem) {
        this.entityItem = entityItem;
        this.beanItem = null;
    }

    public ItemWrapper(BeanItem<T> beanItem) {
        this.entityItem = null;
        this.beanItem = beanItem;
    }

    public EntityItem<T> asEntityItem() {
        return entityItem;
    }

    public BeanItem<T> asBeanItem() {
        return beanItem;
    }

    public T getEntity() {
        return (entityItem == null) ? beanItem.getBean() : entityItem
                .getEntity();
    }

    public Item asItem() {
        return (entityItem == null) ? beanItem : entityItem;
    }
}