package br.com.glandata.nf.main;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.model.NotaFiscal;
import br.com.glandata.nf.util.JPAUtil;

public class PerformanceDeConsultas {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		NotaFiscalDao nfFiscalDao =  new NotaFiscalDao(em);
		
		NotaFiscal notaFiscal = nfFiscalDao.buscaNotaFiscalComCliente((long)1);
		
		em.close();
		
		System.out.println(notaFiscal.getCliente().getNome());
		
	}

}

