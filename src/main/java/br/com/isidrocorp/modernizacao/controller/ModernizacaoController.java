package br.com.isidrocorp.modernizacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.modernizacao.dao.ModernizacaoDAO;
import br.com.isidrocorp.modernizacao.model.Modernizacao;

@RestController
@CrossOrigin("*")
public class ModernizacaoController {
	
	@Autowired
	ModernizacaoDAO dao;
	
	@PostMapping("/modernizacao/nova")
	public ResponseEntity<Modernizacao> novaModernizacao(@RequestBody Modernizacao nova){
		try {
			dao.save(nova);
			return ResponseEntity.status(201).body(nova);
		}
		catch(Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

}
