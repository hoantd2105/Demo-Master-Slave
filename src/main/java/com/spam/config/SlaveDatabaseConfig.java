package com.spam.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.jpa.autoconfigure.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.spam.repository.slave",
        transactionManagerRef = "transactionManagerSlave",
        entityManagerFactoryRef = "entityManagerFactorySlave"
)
@EnableTransactionManagement
public class SlaveDatabaseConfig {
    @Bean(name = "datasourceSlave")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("rootpass");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3307/hoanga");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean(name = "entityManagerFactorySlave")
    public LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory(
            final EntityManagerFactoryBuilder builder, @Qualifier("datasourceSlave") final DataSource dataSource,
            final JpaProperties jpaProperties)
    {
        return builder
                .dataSource(dataSource)
                .packages("com.spam.domain.slave") // định nghĩa nơi chưa entity
                .mappingResources(jpaProperties.getMappingResources().toArray(new String[0]))
                .persistenceUnit("slave")
                .build();
    }

    @Bean(name = "transactionManagerSlave")
    public JpaTransactionManager defaultTransactionManager(
            @Qualifier("entityManagerFactorySlave") final EntityManagerFactory emf)
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
