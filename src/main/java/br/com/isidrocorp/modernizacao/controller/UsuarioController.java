package br.com.isidrocorp.modernizacao.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.modernizacao.dao.UsuarioDAO;
import br.com.isidrocorp.modernizacao.model.Usuario;

@RestController
@CrossOrigin("*")  // aceite requisições de quaiser origens
public class UsuarioController {

	@Autowired // injeção de dependências
	UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ArrayList<Usuario> recuperarTodos(){
		ArrayList<Usuario> lista;
		
		lista = (ArrayList<Usuario>)dao.findAll();
		
		return lista;	
	}
	
	/* Funcionalidade de login:
	 * Por que POST Mapping? - para que o browser possa enviar as infos sensíveis para o back end
	 * Como eu passo essas informações via POST para o meu back end?
	 * 	   vamos precisar passar informações de usuario e senha para esse back/end
	 * 	   Pergunta: temos algum tipo de dado que possui esses atributos?
	 * 			     Sim -> a classe Usuario
	 * 				 Nao -> teríamos que criar uma nova classe
	 *     decisao de projeto: já que a classe Usuario tem Email, Racf e Senha, vamos utiliza-la
	 *     
	 *     Porém precisamos anotar ess parâmetro de entrada com @RequestBody para o spring boot
	 *     entender que ele vai pegar essas informações do corpo da requisição post
	 *     
	 *     o retorno, como traz, além das informações do usuario, o código de retorno http,
	 *     precisa ser declarado como ResponseEntity
	 *     ResponseEntity é a classe do SpringBoot que permite manipularmos os códigos de retorno http
	 */
	@PostMapping("/login")
	public ResponseEntity<Usuario> fazerLogin(@RequestBody Usuario dadosLogin) {
		Usuario resultado;
		
		// aqui eu faço a recuperação no banco a partir dos dados de login q recebi
		resultado = dao.findByRacfOrEmail(dadosLogin.getRacf(), dadosLogin.getEmail());
		
		if (resultado != null) { // o usuario foi efetivamente encontrado, preciso conferir a senha
			
			// as senhas coincidem??
			if (dadosLogin.getSenha().equals(resultado.getSenha())) {
				resultado.setSenha("*********");
				return ResponseEntity.ok(resultado);
			}
			else { // usuario existe, mas senha não coincide... retorno 403
				return ResponseEntity.status(403).build();
			}
		}
		else { // nao foi encontrado, devo retornar um código 404
			return ResponseEntity.notFound().build();
		}
	}
}
