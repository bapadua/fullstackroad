package br.com.ionic.api.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ionic.api.domain.Cliente;
import br.com.ionic.api.domain.dto.ClienteNewDTO;
import br.com.ionic.api.domain.enums.Perfil;
import br.com.ionic.api.repository.ClienteRepository;
import br.com.ionic.api.resource.exception.FieldMessage;
import br.com.ionic.api.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	@Autowired
	private ClienteRepository cliente;
	
	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>(); 
		
		if(dto.getTipo() == null) {
			list.add(new FieldMessage("tipo", "O campo tipo não pode ser nulo"));
		}
		
		if(dto.getTipo().equals(Perfil.CLIENTE.getCod()) && !BR.isValidCPF(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		}
		
		if(dto.getTipo().equals(Perfil.ADMIN.getCod()) && !BR.isValidTCNPJ(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
		}
		
		Cliente aux = cliente.findByEmail(dto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","Email já existente"));
		}
		for(FieldMessage fm : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fm.getMessage())
			.addPropertyNode(fm.getFieldName())
			.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}
