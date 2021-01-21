package br.com.isidrocorp.modernizacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.modernizacao.dao.ComunidadeDAO;
import br.com.isidrocorp.modernizacao.model.Comunidade;

@RestController
@CrossOrigin("*")
public class ComunidadeController {

	// vou precisar acessar o banco de dados?
	@Autowired
	ComunidadeDAO dao;
	
	// vamos criar um método para buscar uma comunidade pelo ID
	
	@GetMapping("/comunidades/{id}")
	public Comunidade recuperarPeloId(@PathVariable int id) {
		return dao.findById(id).get();
	}
}
