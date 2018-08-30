package br.com.ionic.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ionic.api.domain.Cliente;
import br.com.ionic.api.repository.ClienteRepository;
import br.com.ionic.api.service.exception.ObjectNotFoundException;

@Service
public class ClienteService{
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " 
		+ id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
