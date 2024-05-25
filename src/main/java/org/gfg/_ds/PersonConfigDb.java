package org.gfg._ds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = {"org.gfg._ds.Person"},
        entityManagerFactoryRef = "getPersonEntityManager",
        transactionManagerRef = "personTxnManager")
public class PersonConfigDb {

    @Value("${person.datasource.ddl}")
    private String personDDL;

    @Bean
    @ConfigurationProperties(
            prefix = "person.datasource"
    )
    public DataSource getDatasourceForPerson(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getPersonEntityManager(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDatasourceForPerson());
        em.setPackagesToScan("org.gfg._ds.Person");


        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", personDDL);
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");


        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager personTxnManager(){
        JpaTransactionManager txnManager = new JpaTransactionManager();
        txnManager.setEntityManagerFactory(getPersonEntityManager().getObject());
        return txnManager;
    }



}
