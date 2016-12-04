package org.j.products.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew on 11/9/16.
 */

@Entity
@Table(name = "products")
public class Product extends NamedEntity {

    @Column(name = "price", nullable = false)
    private Long price;

    public Long getPrice() {
        return price;
    }

    public void setPrice(final Long price) {
        this.price = price;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "items_values",
        joinColumns = {
            @JoinColumn(
                    name = "item_id",
                    referencedColumnName = "id"
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                    name = "value_id",
                    referencedColumnName = "id"
            )
        }
    )
    private Set<PropertyValue> values = new HashSet<PropertyValue>();

    public Set<PropertyValue> getValues() {
        return values;
    }

    public void setValues(final Set<PropertyValue> values) {
        this.values = values;
    }

    public void addValue(final PropertyValue value) {
        values.add(value);
    }

    public void removeValue(final PropertyValue value) {
        values.remove(value);
    }

    public Set<Property> getProperties() {
        final Set<Property> properties = new HashSet<>();

        values.forEach(value -> properties.add(value.getProperty()));

        return properties;
    }
}
