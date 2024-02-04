package com.hcm.mistry.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "serversEntityManager", transactionManagerRef = "serversTransactionManager", basePackages = {
		"com.hcm.mistry.repo.mysql" })
public class MysqlConfig {
	@Bean(name = "serversEntityManager")
	public LocalContainerEntityManagerFactoryBean getServersEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier("serversDataSource") DataSource serversDataSource) {

		return builder.dataSource(serversDataSource).packages("com.hcm.mistry.mysql.entity")
				.persistenceUnit("servers").properties(additionalJpaProperties()).build();

	}

	Map<String, ?> additionalJpaProperties() {
		Map<String, String> map = new HashMap<>();
		map.put("hibernate.hbm2ddl.auto", "update");
		map.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		map.put("hibernate.show_sql", "true");
		return map;
	}

	@Bean("serversDataSourceProperties")
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties serversDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean("serversDataSource")
	@Primary
	@ConfigurationProperties("spring.datasource")
	public DataSource serversDataSource(
			@Qualifier("serversDataSourceProperties") DataSourceProperties serversDataSourceProperties) {
		return serversDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = "serversTransactionManager")
	public JpaTransactionManager transactionManager(
			@Qualifier("serversEntityManager") EntityManagerFactory serversEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(serversEntityManager);

		return transactionManager;
	}

}