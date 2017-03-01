package org.j.products.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Andrew on 11/10/16.
 */
@MappedSuperclass
public class NamedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s{id=%d, name=%s}", getClass(), getId(), getName());
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(final Object that) {
        if (that == null || getClass() != that.getClass()) {
            return false;
        }

        NamedEntity obj = (NamedEntity) that;

        return this == that || id.equals(obj.getId());
    }
}
