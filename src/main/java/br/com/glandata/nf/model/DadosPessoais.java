package br.com.glandata.nf.model;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
public class DadosPessoais {
	
	public DadosPessoais() {
	}
	
	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	@Getter
	private String nome;
	
	@Getter
	private String cpf;

}
