package br.com.ionic.api.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.ionic.api.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage obj);
}
