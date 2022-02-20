package br.com.glandata.nf.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {
	
	public NotaFiscal() {
	}
	
	public NotaFiscal(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public NotaFiscal(Long id) {
		this.id = id;
	}

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	@Getter @Setter
	private LocalDate data = LocalDate.now();

	@Getter @Setter
	@ManyToOne
	private Cliente cliente;
	
	@Getter @Setter
	@OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL)
	private List<ItemNotaFiscal> itens = new ArrayList<>();
	
	public void adicionarItem(ItemNotaFiscal item) {
		item.setNotaFiscal(this);
		this.itens.add(item);
		
	}
	
	public void calculaTotalNotaFiscal() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		
			for(ItemNotaFiscal item : this.getItens()) {
				valorTotal = valorTotal.add((item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade()))));
			}
			
		this.valorTotal = valorTotal;
	}

}




