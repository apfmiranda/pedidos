package com.apfmiranda.backendpedidos.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.apfmiranda.backendpedidos.services.DBSevice;
import com.apfmiranda.backendpedidos.services.EmailService;
import com.apfmiranda.backendpedidos.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBSevice dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		if (!"create".equals(strategy)) {
			return false;
		}

		dbService.instantiateTestDatabase();

		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
