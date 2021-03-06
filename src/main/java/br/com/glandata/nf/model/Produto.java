package br.com.glandata.nf.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQuery(
		name = "Produto.produtosPorCategoria",
		query = "SELECT p FROM Produto p WHERE p.categoria.id.nome = :nome"
		)
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
	
	public Produto() {
	}
	
	public Produto(Long id) {
		this.id = id;
	}
	
	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter @Setter
	private String nome;

	@Getter @Setter
	private String descricao;

	@Getter @Setter
	private BigDecimal preco;
	
	@Getter @Setter
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;
	
	@Getter @Setter
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro = LocalDate.now();

}







