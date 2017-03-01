package org.j.products.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Andrew on 12/29/16.
 */

@Configuration
@PropertySource("classpath:data.properties")
@EnableJpaRepositories(basePackages = "org.j.products.repositories")
@EnableTransactionManagement
public class DataConfig {

    @Autowired
    private Environment env;

    @Bean
    @Profile("prod")
    public DataSource dataSource() {
        final BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getRequiredProperty("db.driver"));
        ds.setUrl(env.getRequiredProperty("db.url"));
        ds.setUsername(env.getRequiredProperty("db.user"));
        ds.setPassword(env.getRequiredProperty("db.password"));
        return ds;
    }

    @Bean
    @Profile("prod")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource ds) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(ds);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        entityManagerFactoryBean.setPackagesToScan("org.j.products.entities");

        final Properties hbmProperties = new Properties();
        hbmProperties.setProperty("hibernate.show_sql", env.getRequiredProperty("hbm.show_sql"));
        hbmProperties.setProperty("hibernate.format_sql", env.getRequiredProperty("hbm.format_sql"));
        hbmProperties.setProperty("hibernate.dialect", env.getRequiredProperty("hbm.dialect"));
        hbmProperties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hbm.hbm2ddl.auto"));

        entityManagerFactoryBean.setJpaProperties(hbmProperties);

        return entityManagerFactoryBean;
    }

    @Bean(name = "dataSource")
    @Profile("test")
    public DataSource testDataSource() {
        final BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getRequiredProperty("db.test.driver"));
        ds.setUrl(env.getRequiredProperty("db.test.url"));
        ds.setUsername(env.getRequiredProperty("db.test.user"));
        ds.setPassword(env.getRequiredProperty("db.test.password"));
        return ds;
    }

    @Bean(name = "entityManagerFactory")
    @Profile("test")
    public LocalContainerEntityManagerFactoryBean testEntityManagerFactory(final DataSource ds) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(ds);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        entityManagerFactoryBean.setPackagesToScan("org.j.products.entities");

        final Properties hbmProperties = new Properties();
        hbmProperties.setProperty("hibernate.show_sql", env.getRequiredProperty("hbm.test.show_sql"));
        hbmProperties.setProperty("hibernate.format_sql", env.getRequiredProperty("hbm.test.format_sql"));
        hbmProperties.setProperty("hibernate.dialect", env.getRequiredProperty("hbm.test.dialect"));
        hbmProperties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hbm.test.hbm2ddl.auto"));
        hbmProperties.setProperty("hibernate.hbm2ddl.import_files", env.getRequiredProperty("hbm.test.import_files"));

        entityManagerFactoryBean.setJpaProperties(hbmProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
