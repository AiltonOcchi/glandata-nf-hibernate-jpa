package br.com.glandata.nf.main;

import javax.persistence.EntityManager;

import br.com.glandata.nf.model.NotaFiscal;
import br.com.glandata.nf.util.JPAUtil;

public class PerformanceDeConsultas {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		NotaFiscal nf = em.find(NotaFiscal.class, (long)1);
		
		System.out.println(nf.getItens().size());
		
	}

}