package br.com.ionic.api.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.ionic.api.domain.Cliente;
import br.com.ionic.api.domain.dto.ClienteDTO;
import br.com.ionic.api.repository.ClienteRepository;
import br.com.ionic.api.resource.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository cliente;

	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		@SuppressWarnings("unused")
		Integer uriId = Integer.parseInt(map.get("id"));
		
		
		
		List<FieldMessage> list = new ArrayList<>(); 
		Cliente aux = cliente.findByEmail(dto.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email","Email em uso"));
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
