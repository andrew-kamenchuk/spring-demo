package org.j.products.search.products;

import org.springframework.data.domain.Sort;

/**
 * Created by Andrew on 2/15/17.
 */
public class SortOrder extends Sort.Order {

    private final String name;

    public SortOrder(final String name, final Sort.Direction direction, final String property) {
        super(direction, property);
        this.name = name;
    }

    public SortOrder(final String name, final String property) {
        super(property);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s.%s", getProperty(), getDirection().toString());
    }
}
