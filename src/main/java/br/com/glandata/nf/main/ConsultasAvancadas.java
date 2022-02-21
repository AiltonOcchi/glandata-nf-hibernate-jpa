package br.com.glandata.nf.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.dao.ProdutoDao;
import br.com.glandata.nf.model.Produto;
import br.com.glandata.nf.util.JPAUtil;
import br.com.glandata.nf.vo.RelatorioFaturamentoVo;

public class ConsultasAvancadas {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		
		NotaFiscalDao notFiscalDao =  new NotaFiscalDao(em);
		ProdutoDao produtoDao =  new ProdutoDao(em);
		
		// IMPRIME O TOTAL DAS NOTAS
		BigDecimal totalFaturado = notFiscalDao.totalFaturado();
		System.out.println("\nO valor total faturado é: "+totalFaturado+"\n");
		
		// IMPRIME O RELATÓRIO DE VENDAS
		List<RelatorioFaturamentoVo> relatorio = notFiscalDao.relatorioDeFaturamento();
		
		System.out.println("\n\n################ RELATÓRIO DE VENDAS ###############");
		System.out.println("PRODUTO \t\t\tQTD VENDIDA \t\tÚltima Venda");
		
		for (RelatorioFaturamentoVo result : relatorio) {
			System.out.println(result.getNomeProduto()+ "\t\t\t\t"+result.getQuantidadeVendida()+ "\t\t\t"+result.getDataUltimaCompra());
		}
		
		// IMPRIME LISTA PELO NAMED QUERY
		List<Produto> produtosPorNomeDaCategoria = produtoDao.buscarPorNomeDaCategoria("TELEVISORES");
		System.out.println("\n#########RELATORIO DE PRODUTOS POR CATEGORIA#######");
		produtosPorNomeDaCategoria.forEach(p -> System.out.println(p.getNome()));
		
		em.close();

	}

}

