package br.com.glandata.nf.main;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.util.JPAUtil;

public class ConsultasAvancadas {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		NotaFiscalDao notFiscalDao =  new NotaFiscalDao(em);
		
		BigDecimal totalFaturado = notFiscalDao.totalFaturado();
		System.out.println("O valor total faturado é: "+totalFaturado);
		
		em.close();

	}

}

