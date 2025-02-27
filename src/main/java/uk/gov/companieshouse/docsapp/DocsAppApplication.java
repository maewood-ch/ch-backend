package uk.gov.companieshouse.docsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// enables database and transactions
@EnableJpaRepositories(basePackages = "uk.gov.companieshouse.docsapp.dao")
@EnableTransactionManagement
public class DocsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocsAppApplication.class, args);
	}

}

// Committing this comment to see Concourse pipeline trigger a `companies` job