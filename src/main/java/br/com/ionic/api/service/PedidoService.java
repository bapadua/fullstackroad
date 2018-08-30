package br.com.ionic.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ionic.api.domain.Pedido;
import br.com.ionic.api.repository.PedidoRepository;
import br.com.ionic.api.service.exception.ObjectNotFoundException;

@Service
public class PedidoService{
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido findPedido(Integer id){
		Optional<Pedido> obj = this.pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " 
				+ id + ", Tipo: " + Pedido.class.getName()));
	}
}
