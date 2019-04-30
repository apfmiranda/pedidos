package com.apfmiranda.backendpedidos.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.apfmiranda.backendpedidos.services.DBSevice;
import com.apfmiranda.backendpedidos.services.EmailService;
import com.apfmiranda.backendpedidos.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBSevice dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
	    JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
//	    mailSenderImpl.setSession(session());
	    return mailSenderImpl;
	}

}
