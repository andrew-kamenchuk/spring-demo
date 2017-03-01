package org.j.products.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Andrew on 11/23/16.
 */
@Entity
@Table(name = "properties_values")
public class PropertyValue extends NamedEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_prop"), nullable = false)
    private Property property;

    public Property getProperty() {
        return property;
    }

    public void setProperty(final Property property) {
        this.property = property;
    }
}
