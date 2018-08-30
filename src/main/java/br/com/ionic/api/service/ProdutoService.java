package br.com.ionic.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ionic.api.domain.Produto;
import br.com.ionic.api.repository.ProdutoRepository;

@Service
public class ProdutoService{
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ResponseEntity<?> findProduto(Integer id){
		Optional<Produto> obj = this.produtoRepository.findById(id);
		return ResponseEntity.ok().body(obj.orElse(null));
	}
}
