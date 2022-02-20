package br.com.glandata.nf.main;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.ClienteDao;
import br.com.glandata.nf.dao.NotaFiscalDao;
import br.com.glandata.nf.dao.ProdutoDao;
import br.com.glandata.nf.model.Cliente;
import br.com.glandata.nf.model.ItemNotaFiscal;
import br.com.glandata.nf.model.NotaFiscal;
import br.com.glandata.nf.model.Produto;
import br.com.glandata.nf.util.JPAUtil;

public class GeraNotaFiscal {

	public static void main(String[] args) {
		
		PopulaDados.cadastraDadosBase();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ClienteDao clienteDao = new ClienteDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		NotaFiscalDao notaFiscalDao = new NotaFiscalDao(em);
		
		// Este método não é necessário porque na NotaFiscal Mapeamos o ItemNotaFiscal como cascade = CascadeType.ALL
		//ItemNotaFiscalDao itemNotaFiscalDao = new ItemNotaFiscalDao(em);
		
		em.getTransaction().begin();
		
		Cliente cliente1 = clienteDao.buscarPorId((long)1);
		
		Produto produto3 = produtoDao.buscarPorId((long)3);
		Produto produto1 = produtoDao.buscarPorId((long)1);
		Produto produto2 = produtoDao.buscarPorId((long)2);
		
		NotaFiscal notaFiscal = new NotaFiscal(cliente1);
		notaFiscal.adicionarItem(new ItemNotaFiscal(2, notaFiscal, produto1));
		notaFiscal.adicionarItem(new ItemNotaFiscal(1, notaFiscal, produto3));
		notaFiscal.adicionarItem(new ItemNotaFiscal(4, notaFiscal, produto2));
		
		notaFiscal.calculaTotalNotaFiscal();
		
		notaFiscalDao.cadastrar(notaFiscal);
		
		em.getTransaction().commit();
		em.close();
		
	}

}


