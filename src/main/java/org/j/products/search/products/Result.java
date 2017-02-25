package org.j.products.search.products;

import org.j.products.entities.Product;
import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * Created by Andrew on 2/6/17.
 */
public interface Result {
    Page<Product> getProducts();

    Set<PropertyFacetResult> getFacets();
}
