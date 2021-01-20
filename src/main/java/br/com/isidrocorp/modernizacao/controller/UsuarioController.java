package br.com.isidrocorp.modernizacao.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.modernizacao.dao.UsuarioDAO;
import br.com.isidrocorp.modernizacao.model.Usuario;

@RestController
public class UsuarioController {

	@Autowired // injeção de dependências
	UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ArrayList<Usuario> recuperarTodos(){
		ArrayList<Usuario> lista;
		
		lista = (ArrayList<Usuario>)dao.findAll();
		
		return lista;	
	}
	
	@GetMapping("/login")
	public Usuario fazerLogin() {
		Usuario user = dao.findByRacfAndSenha("isidro", "12345");
		return user;
	}
}
