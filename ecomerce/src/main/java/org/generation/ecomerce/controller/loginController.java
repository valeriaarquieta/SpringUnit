package org.generation.ecomerce.controller;

import java.util.Date;
import java.util.Calendar;

import javax.servlet.ServletException;

import org.generation.ecomerce.config.JwtFilter;
import org.generation.ecomerce.model.Token;
import org.generation.ecomerce.model.Usuario;
import org.generation.ecomerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(path="/api/login/")
public class loginController {
private final UsuarioService usuarioservice;

	@Autowired
	public loginController( UsuarioService usuarioservice) 
	{this.usuarioservice=usuarioservice;}//cont
		
	@PostMapping
	public Token loginUsuario(@RequestBody Usuario usuario)
			throws ServletException{
			if(usuarioservice.validateUsuario(usuario)) {
				return new Token(generateToken(usuario.getEmail()));
		}//ifvalidate
			throw new ServletException("Nombre o contraseña incorrectos");
	}//login
	private String generateToken (String username) {
		Calendar calendar =Calendar.getInstance();
		calendar.add(Calendar.HOUR,10);
		return Jwts.builder().setSubject(username).claim("role", "user")
		.setIssuedAt(new Date()).setExpiration(calendar.getTime())
		.signWith(SignatureAlgorithm.HS256,JwtFilter.secret).compact();
	}//GENERATEtOKEN
}//class
