package com.gerenciapedidos.herbert.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciapedidos.herbert.dto.LoginDTO;
import com.gerenciapedidos.herbert.infra.TokenJWTDTO;
import com.gerenciapedidos.herbert.infra.TokenService;
import com.gerenciapedidos.herbert.usuarios.Usuario;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
		
	@PostMapping
	public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
		//TODO: process POST request
		var token = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.senha());
		var autenticacao = manager.authenticate(token);
		
		var tokenJWT = tokenService.gerarToken((Usuario)autenticacao.getPrincipal());
		
		return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
		
	}
	
}
