package org.j.products.solr.document;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Andrew on 1/9/17.
 */
@SolrDocument(solrCoreName = "products")
public class ProductSolrDocument {

    @Id
    @Indexed
    @Field("id")
    private Long id;

    @Indexed
    @Field("name")
    private String name;

    @Indexed
    @Field("price")
    private Long price;

    @Indexed
    @Field("description")
    private Collection<String> description;

    @Dynamic
    @Field("prop_*")
    private Map<String, Long> dynamicProps;

    public ProductSolrDocument() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Collection<String> getDescription() {
        return description;
    }

    public void setDescription(Collection<String> description) {
        this.description = description;
    }

    public Map<String, Long> getDynamicProps() {
        return dynamicProps;
    }

    public void setDynamicProps(Map<String, Long> dynamicProps) {
        this.dynamicProps = dynamicProps;
    }
}
