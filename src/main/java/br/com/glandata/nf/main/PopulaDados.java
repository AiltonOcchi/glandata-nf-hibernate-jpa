package br.com.glandata.nf.main;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.glandata.nf.dao.CategoriaDao;
import br.com.glandata.nf.dao.ClienteDao;
import br.com.glandata.nf.dao.ProdutoDao;
import br.com.glandata.nf.model.Categoria;
import br.com.glandata.nf.model.Cliente;
import br.com.glandata.nf.model.Produto;
import br.com.glandata.nf.util.JPAUtil;

public class PopulaDados {

	public static void main(String[] args) {
		cadastraDadosBase();
	}

	public static void cadastraDadosBase() {
		EntityManager em = JPAUtil.getEntityManager();
		
		ClienteDao clienteDao = new ClienteDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Cliente cliente1 = new Cliente("Douglas", "456789");
		Cliente cliente2 = new Cliente("Sandra", "789456");
		
		Categoria categoria1 = new Categoria("CELULARES");
		Categoria categoria2 = new Categoria("TELEVISORES");
		
		em.getTransaction().begin();
		
		clienteDao.cadastrar(cliente1);// poderia ser clienteDao.cadastrar(new Cliente("Ailton", "123456"));
		clienteDao.cadastrar(cliente2);
		
		categoriaDao.cadastrar(categoria1);// poderia ser categoriaDao.cadastrar( new Categoria("CELULARES"));
		categoriaDao.cadastrar(categoria2);
		
		produtoDao.cadastrar(new Produto("Moto G30", "Celular 4G", new BigDecimal("1200.00"), categoria1));
		produtoDao.cadastrar(new Produto("Moto G100", "Celular 5G", new BigDecimal("2000.00"), categoria1));
		produtoDao.cadastrar(new Produto("TV Samsung 50", "Televisor de 50 Polegadas", new BigDecimal("2000.00"), categoria2));
		produtoDao.cadastrar(new Produto("TV Samsung 60", "Televisor de 60 Polegadas", new BigDecimal("3000.00"), categoria2));
		
		em.getTransaction().commit();
		em.close();
	}

}

