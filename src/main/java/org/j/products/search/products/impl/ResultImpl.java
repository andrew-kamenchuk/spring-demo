package org.j.products.search.products.impl;

import org.j.products.entities.Product;
import org.j.products.entities.Property;
import org.j.products.entities.PropertyValue;
import org.j.products.search.products.Result;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Created by Andrew on 2/9/17.
 */
public class ResultImpl implements Result {
    private Page<Product> productPage;

    private Map<Property, Map<PropertyValue, Long>> facetStats;

    public ResultImpl() {

    }

    public ResultImpl(final Page<Product> productPage, final Map<Property, Map<PropertyValue, Long>> facetStats) {
        this.productPage = productPage;
        this.facetStats = facetStats;
    }

    public Page<Product> getProducts() {
        return productPage;
    }

    public Map<Property, Map<PropertyValue, Long>> getFacetStats() {
        return facetStats;
    }
}
