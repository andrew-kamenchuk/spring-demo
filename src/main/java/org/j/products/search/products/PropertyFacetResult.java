package org.j.products.search.products;

import org.j.products.entities.Property;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Andrew on 2/22/17.
 */
public class PropertyFacetResult {
    private Property property;

    private Set<PropertyValueFacetResult> propertyValueFacetResults;

    private Boolean selected;

    public PropertyFacetResult(final Property property, final Boolean selected,
                               final Set<PropertyValueFacetResult> propertyValueFacetResults) {
        setProperty(property);
        setSelected(selected);
        setPropertyValueFacetResults(propertyValueFacetResults);
    }

    public PropertyFacetResult(final Property property, final Boolean selected) {
        this(property, selected, new LinkedHashSet<>());
    }

    public PropertyFacetResult(final Property property) {
        this(property, false);
    }

    public Property getProperty() {
        return property;
    }

    public Set<PropertyValueFacetResult> getPropertyValueFacetResults() {
        return propertyValueFacetResults;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void addPropertyValueFacetResult(final PropertyValueFacetResult propertyValueFacetResult) {
        propertyValueFacetResults.add(propertyValueFacetResult);
    }

    public void setProperty(final Property property) {
        this.property = property;
    }

    public void setPropertyValueFacetResults(final Set<PropertyValueFacetResult> propertyValueFacetResults) {
        this.propertyValueFacetResults = propertyValueFacetResults;
    }

    public void setSelected(final Boolean selected) {
        this.selected = selected;
    }
}
