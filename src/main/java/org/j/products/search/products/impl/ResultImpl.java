package org.j.products.search.products.impl;

import org.j.products.entities.Product;
import org.j.products.search.products.PropertyFacetResult;
import org.j.products.search.products.Result;
import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * Created by Andrew on 2/9/17.
 */
public class ResultImpl implements Result {
    private Page<Product> productPage;

    private Set<PropertyFacetResult> facets;

    public ResultImpl() {

    }

    public ResultImpl(final Page<Product> productPage, final Set<PropertyFacetResult> facets) {
        this.productPage = productPage;
        this.facets = facets;
    }

    public Page<Product> getProducts() {
        return productPage;
    }

    public Set<PropertyFacetResult> getFacets() {
        return facets;
    }
}
