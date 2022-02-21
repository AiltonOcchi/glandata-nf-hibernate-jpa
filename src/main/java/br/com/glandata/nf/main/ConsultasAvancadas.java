package br.com.glandata.nf.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.util.JPAUtil;
import br.com.glandata.nf.vo.RelatorioFaturamentoVo;

public class ConsultasAvancadas {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		NotaFiscalDao notFiscalDao =  new NotaFiscalDao(em);
		
		// IMPRIME O TOTAL DAS NOTAS
		BigDecimal totalFaturado = notFiscalDao.totalFaturado();
		System.out.println("\nO valor total faturado é: "+totalFaturado+"\n");
		
		// IMPRIME O RELATÓRIO DE VENDAS
		List<RelatorioFaturamentoVo> relatorio = notFiscalDao.relatorioDeFaturamento();
		
		System.out.println("\n\n################ RELATÓRIO DE VENDAS ###############");
		System.out.println("PRODUTO \t\tQTD VENDIDA \tÚltima Venda");
		
		for (RelatorioFaturamentoVo result : relatorio) {
			System.out.println(result.getNomeProduto()+ "\t\t"+result.getQuantidadeVendida()+ "\t\t"+result.getDataUltimaCompra());
		}
		
		em.close();

	}

}

