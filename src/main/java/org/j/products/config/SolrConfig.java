package org.j.products.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * Created by Andrew on 1/9/17.
 */
@Configuration
@PropertySource("classpath:solr.properties")
@EnableSolrRepositories(basePackages = "org.j.products.solr.repositories", multicoreSupport = true)
public class SolrConfig {

    @Autowired
    private Environment env;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient(env.getRequiredProperty("solr.base_url"));
    }
}
