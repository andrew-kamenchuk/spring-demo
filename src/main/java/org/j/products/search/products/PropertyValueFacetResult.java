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
        this.propertyValue = propertyValue;
        this.count = count;
        this.selected = selected;
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
}
