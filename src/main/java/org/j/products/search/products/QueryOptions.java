package org.j.products.search.products;

import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrew on 2/6/17.
 */
public class QueryOptions {

    private SearchService searchService;

    private String query;

    private Long minPrice;
    private Long maxPrice;

    private final Map<Long, Set<Long>> filterValues = new HashMap<>();

    public QueryOptions() {

    }

    public QueryOptions(final SearchService searchService) {
        setSearchService(searchService);
    }

    public void setSearchService(final SearchService searchService) {
        this.searchService = searchService;
    }

    public SearchService getSearchService() {
        return this.searchService;
    }

    public Result search(final Pageable pageable) {
        return searchService.search(this, pageable);
    }

    public String getQuery() {
        return query;
    }

    public QueryOptions setQuery(final String query) {
        this.query = query;

        return this;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public QueryOptions setMinPrice(final Long minPrice) {
        this.minPrice = minPrice;

        return this;
    }

    public Long getMaxPrice() {
        return maxPrice;
    }

    public QueryOptions setMaxPrice(final Long maxPrice) {
        this.maxPrice = maxPrice;

        return this;
    }

    public Map<Long, Set<Long>> getFilterValues() {
        return filterValues;
    }

    public QueryOptions addFilterValue(final Long propertyId, final Long valueId) {
        if (!filterValues.containsKey(propertyId)) {
            filterValues.put(propertyId, new HashSet<>());
        }

        filterValues.get(propertyId).add(valueId);

        return this;
    }
}
