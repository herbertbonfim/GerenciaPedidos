package com.gerenciapedidos.herbert.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TrataErros {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> trataErro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(Erros::new).toList());
	}
	
	public record Erros(String campo, String mensagem) {
		
		public Erros(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
		
	}

}
