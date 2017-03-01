package org.j.products.search.products;

import org.j.products.entities.PropertyValue;

/**
 * Created by Andrew on 2/22/17.
 */
public class PropertyValueFacetResult {

    private PropertyValue propertyValue;

    private Long count;

    private Boolean selected;

    public PropertyValueFacetResult(final PropertyValue propertyValue, final Long count, final Boolean selected) {
        setPropertyValue(propertyValue);
        setCount(count);
        setSelected(selected);
    }

    public PropertyValueFacetResult(final PropertyValue propertyValue, final Long count) {
        this(propertyValue, count, false);
    }

    public PropertyValue getPropertyValue() {
        return propertyValue;
    }

    public Long getCount() {
        return count;
    }

    public Boolean getSelected() {
        return selected;
    }


    public void setPropertyValue(final PropertyValue propertyValue) {
        this.propertyValue = propertyValue;
    }

    public void setCount(final Long count) {
        this.count = count;
    }

    public void setSelected(final Boolean selected) {
        this.selected = selected;
    }
}
