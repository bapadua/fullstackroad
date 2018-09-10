package br.com.ionic.api.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.ionic.api.domain.dto.ClienteNewDTO;
import br.com.ionic.api.domain.enums.TipoCliente;
import br.com.ionic.api.resource.exception.FieldMessage;
import br.com.ionic.api.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>(); 
		
		if(dto.getTipo() == null) {
			list.add(new FieldMessage("tipo", "O campo tipo não pode ser nulo"));
		}
		
		if(dto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		}
		
		if(dto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidTCNPJ(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
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
