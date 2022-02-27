package br.com.glandata.nf.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Chinelo extends Produto {
	
	public Chinelo() {
	}
	
	public Chinelo(String marca, Integer tamanho) {
		this.marca = marca;
		this.tamanho = tamanho;
	}

	@Getter @Setter
	private String marca;
	
	@Getter @Setter
	private Integer tamanho;
	
}

