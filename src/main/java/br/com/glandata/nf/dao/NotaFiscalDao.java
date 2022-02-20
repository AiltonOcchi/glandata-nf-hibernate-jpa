package br.com.glandata.nf.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.glandata.nf.model.NotaFiscal;

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

}

