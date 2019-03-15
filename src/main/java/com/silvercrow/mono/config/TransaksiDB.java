package com.silvercrow.mono.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "trxEntityManagerFactory",
        transactionManagerRef = "trxTransactionManager",
        basePackages = { "com.silvercrow.mono.transaksi.repo" }
)
public class TransaksiDB {
    @Autowired
    private Environment env;


    @Bean
    @ConfigurationProperties(prefix = "spring.db2.datasource")
    public DataSource dataSourceTransaksi(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.db2.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.db2.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.db2.datasourcee.username"));
        dataSource.setPassword(env.getProperty("spring.db2.datasource.password"));

        return dataSource;
    }

    @Bean(name = "trxEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    trxEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceTransaksi());
        em.setPackagesToScan("com.silvercrow.mono.transaksi.model");

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("spring.db2.hibernate.dialect",
                env.getProperty("spring.db2.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("spring.db2.hibernate.show_sql"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "trxTransactionManager")
    public PlatformTransactionManager trxTransactionManager(
            @Qualifier("trxEntityManagerFactory") EntityManagerFactory
                    trxEntityManagerFactory
    ) {
        return new JpaTransactionManager(trxEntityManagerFactory);
    }

}
