package org.j.products.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Andrew on 12/31/16.
 */
@Configuration
@Import(DataConfig.class)
@ComponentScan("org.j.products")
public class AppConfig {
}
