package org.j.products.search.products;

import org.springframework.data.domain.Pageable;

/**
 * Created by Andrew on 2/6/17.
 */
public interface SearchService {
    Result search(QueryOptions queryOptions, Pageable pageable);

    default QueryOptions getQueryOptions() {
        return new QueryOptions(this);
    }

    SortOrder[] getAwailableSortOrders();
}
