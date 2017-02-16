package org.j.products.search.products;

import org.j.products.entities.Product;
import org.j.products.entities.Property;
import org.j.products.entities.PropertyValue;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Created by Andrew on 2/6/17.
 */
public interface Result {
    Page<Product> getProducts();

    Map<Property, Map<PropertyValue, Long>> getFacetStats();
}
