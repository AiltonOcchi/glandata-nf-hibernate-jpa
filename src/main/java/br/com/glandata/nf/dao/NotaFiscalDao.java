package br.com.glandata.nf.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.model.NotaFiscal;
import br.com.glandata.nf.vo.RelatorioFaturamentoVo;

public class NotaFiscalDao {
	
	private EntityManager em;

	public NotaFiscalDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(NotaFiscal notaFiscal) {
		em.persist(notaFiscal);
	}
	
	public BigDecimal totalFaturado() {
		String jpql = "SELECT SUM(nf.valorTotal) FROM NotaFiscal nf";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	public List<RelatorioFaturamentoVo> relatorioDeFaturamento(){
		
		String jpql = "SELECT new br.com.glandata.nf.vo.RelatorioFaturamentoVo( "
				+ " produto.nome, "
				+ " SUM (item.quantidade), "
				+ " MAX(notaFiscal.data)) "
				+ " FROM NotaFiscal notaFiscal "
				+ " JOIN notaFiscal.itens item "
				+ " JOIN item.produto produto "
				+ " GROUP BY produto.nome "
				+ " ORDER BY SUM(item.quantidade) DESC";
		
		return em.createQuery(jpql, RelatorioFaturamentoVo.class).getResultList();
	}

}



