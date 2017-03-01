package org.j.products.configuration;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
    @Profile("prod")
    public SolrClient solrClient() {
        return new HttpSolrClient(env.getRequiredProperty("solr.base_url"));
    }

    @Bean
    @Profile("prod")
    public SolrTemplate productsTemplate() {
        return new SolrTemplate(solrClient(), "products");
    }

    @Bean(name = "solrClient")
    @Profile("test")
    public SolrClient testSolrClient() throws IOException, SAXException, ParserConfigurationException {
        return new EmbeddedSolrServerFactory(env.getRequiredProperty("solr.test.home")).getSolrClient();
    }

    @Bean(name = "productsTemplate")
    @Profile("test")
    public SolrTemplate testProductsTemplate() throws ParserConfigurationException, SAXException, IOException {
        return new SolrTemplate(testSolrClient());
    }
}
