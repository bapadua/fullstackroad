package br.com.ionic.api.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ionic.api.domain.Categoria;
import br.com.ionic.api.domain.Cliente;
import br.com.ionic.api.domain.dto.CategoriaDTO;
import br.com.ionic.api.repository.CategoriaRepository;
import br.com.ionic.api.service.exception.DataViolationException;
import br.com.ionic.api.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService{
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: " 
		+ id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll(){
		return this.repository.findAll();
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return this.repository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria newObj = this.find(obj.getId());
		this.updateData(newObj, obj);
		return this.repository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			this.repository.deleteById(id);;
		} catch (ConstraintViolationException e) {
			throw new DataViolationException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.repository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
	
	
	
}
