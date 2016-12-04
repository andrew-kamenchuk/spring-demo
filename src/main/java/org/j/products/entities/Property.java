package org.j.products.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew on 11/21/16.
 */

@Entity
@Table(name = "properties")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Property extends NamedEntity {

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PropertyValue> values = new HashSet<PropertyValue>();

    public Set<PropertyValue> getValues() {
        return values;
    }

    public void setValues(final Set<PropertyValue> values) {
        values.forEach(value -> value.setProperty(this));
        this.values = values;
    }

    public void addValue(final PropertyValue value) {
        value.setProperty(this);
        values.add(value);
    }

    public void removeValue(final PropertyValue value) {
        value.setProperty(null);
        values.remove(value);
    }
}
