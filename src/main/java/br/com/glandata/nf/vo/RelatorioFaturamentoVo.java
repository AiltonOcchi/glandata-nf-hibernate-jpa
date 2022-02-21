package br.com.glandata.nf.vo;

import java.time.LocalDate;

import lombok.Getter;

public class RelatorioFaturamentoVo {
	
	public RelatorioFaturamentoVo(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaCompra) {
		this.nomeProduto = nomeProduto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaCompra = dataUltimaCompra;
	}

	@Getter
	private String nomeProduto;
	
	@Getter
	private Long quantidadeVendida;
	
	@Getter
	private LocalDate dataUltimaCompra;
	
}

