package br.com.glandata.nf.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itens_nota_fiscal")
public class ItemNotaFiscal {
	
	public ItemNotaFiscal() {
	}
	
	public ItemNotaFiscal(Long id) {
		this.id = id;
	}
	
	public ItemNotaFiscal(Integer quantidade, NotaFiscal notaFiscal, Produto produto) {
		this.quantidade = quantidade;
		this.notaFiscal = notaFiscal;
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
	}

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	@Getter @Setter
	private Integer quantidade;
	
	@Getter @Setter
	@ManyToOne
	private NotaFiscal notaFiscal;
	
	@Getter @Setter
	@ManyToOne
	private Produto produto;

}


