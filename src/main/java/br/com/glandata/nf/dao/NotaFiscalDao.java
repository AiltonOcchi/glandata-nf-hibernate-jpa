package br.com.glandata.nf.dao;

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

}

