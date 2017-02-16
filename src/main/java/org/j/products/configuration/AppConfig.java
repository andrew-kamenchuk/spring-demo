package org.j.products.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Andrew on 12/31/16.
 */
@Configuration
@Import({DataConfig.class, SolrConfig.class})
@ComponentScan(basePackages = "org.j.products.search")
public class AppConfig {
}
