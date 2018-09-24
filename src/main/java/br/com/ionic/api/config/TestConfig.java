package br.com.ionic.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ionic.api.service.DbService;
import br.com.ionic.api.service.EmailService;
import br.com.ionic.api.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DbService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws Exception {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
