package br.com.glandata.nf.main;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.ProdutoDao;
import br.com.glandata.nf.model.Produto;
import br.com.glandata.nf.util.JPAUtil;

public class ConsultaAvancadas2 {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao =  new ProdutoDao(em);
		
		String nomeProduto = "Camisa";
		String nomeCategoria = "VESTUARIO";
		var valorInicial = "50";
		var valorMaximo = "500";
		
		List<Produto> produtosPorParametro = produtoDao.buscarPorParametros(nomeProduto, nomeCategoria, valorInicial, valorMaximo);
		
		produtosPorParametro.forEach(p -> System.out.println(p));
		
	}

}

