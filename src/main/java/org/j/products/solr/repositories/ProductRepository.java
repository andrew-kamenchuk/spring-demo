package org.j.products.solr.repositories;

import org.j.products.solr.document.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Andrew on 1/9/17.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
