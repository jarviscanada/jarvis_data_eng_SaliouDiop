package ca.jrvs.apps.tradingapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class})
public class TradingAppApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(TradingAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TradingAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Application started");
	}
}