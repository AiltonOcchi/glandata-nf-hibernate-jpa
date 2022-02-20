package br.com.glandata.nf.dao;

import javax.persistence.EntityManager;

import br.com.glandata.nf.model.ItemNotaFiscal;

public class ItemNotaFiscalDao {
	
	private EntityManager em;

	public ItemNotaFiscalDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(ItemNotaFiscal itemNotaFiscal) {
		em.persist(itemNotaFiscal);
	}

}

