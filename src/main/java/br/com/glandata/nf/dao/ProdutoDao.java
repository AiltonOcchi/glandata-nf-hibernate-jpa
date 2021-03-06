package br.com.glandata.nf.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.common.base.Strings;

import br.com.glandata.nf.model.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Produto produto) {
		em.persist(produto);
	}

	public void atualizar(Produto produto) {
		em.merge(produto);
	}

	public void remover(Produto produto) {
		produto = em.merge(produto);
		em.remove(produto);
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nomeCategoria) {
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
				.setParameter("nome", nomeCategoria) //poderia ser o numero  que representa a interrogação
				.getResultList();
	}
	
	public List<Produto> buscarPorParametros(String nomeProduto, String nomeCategoria, String valorInicial, String valorMaximo) {
		
		String jpql = "SELECT p FROM Produto p WHERE 1=1";
		
		if (!Strings.isNullOrEmpty(nomeProduto))jpql += "AND LOWER(p.nome) LIKE :nomeProduto ";
		if (!Strings.isNullOrEmpty(nomeCategoria))jpql += "AND LOWER(p.categoria.categorianome) LIKE :nomeCategoria ";
		if (!Strings.isNullOrEmpty(valorInicial))jpql += "AND p.preco >= :valorInicial ";
		if (!Strings.isNullOrEmpty(valorMaximo))jpql += "AND p.preco <= :valorMaximo ";
				
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		
		if (!Strings.isNullOrEmpty(nomeProduto)) query.setParameter("nomeProduto", "%"+nomeProduto.toLowerCase()+"%");
		if (!Strings.isNullOrEmpty(nomeCategoria)) query.setParameter("nomeCategoria", "%"+nomeCategoria.toLowerCase()+"%");
		if (!Strings.isNullOrEmpty(valorInicial)) query.setParameter("valorInicial", valorInicial);
		if (!Strings.isNullOrEmpty(valorMaximo)) query.setParameter("valorMaximo", valorMaximo);
		
		return query.getResultList();
		
	}
	
	
	public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
	
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		
		Predicate filtros = builder.and(
				builder.equal(from.get("nome"), nome),
				builder.equal(from.get("preco"), preco),
				builder.equal(from.get("dataCadastro"), dataCadastro)
		);
		
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}
	
}


