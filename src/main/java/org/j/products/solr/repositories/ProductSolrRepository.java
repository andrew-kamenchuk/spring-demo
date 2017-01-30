package org.j.products.solr.repositories;

import org.j.products.solr.document.ProductSolrDocument;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Andrew on 1/9/17.
 */
public interface ProductSolrRepository extends PagingAndSortingRepository<ProductSolrDocument, Long> {
}
