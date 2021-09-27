package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	public void remover(Produto produto) {
		produto=em.merge(produto);
		this.em.remove(produto);
	}
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);//find(Produto.class, id) - utiliza como parametro a entidade e o id
	}
	public List<Produto> buscarTodos(){
		//JPQL = JAVA PERSISTENCE SQL
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();//getResultList() - dispara a query no banco de dados
	}
	public List<Produto> buscarPorNome(String nome){
		//JPQL = JAVA PERSISTENCE SQL
		//String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome"; // 1 abordagem
		String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1"; //pode passa ?1 que significa posicao 1  2 abordagem
		return em.createQuery(jpql, Produto.class)
				.setParameter(1,nome) // subsitui o parametro passado no JPQL
				.getResultList();//getResultList() - dispara a query no banco de dados
	}
	public BigDecimal buscarPorPrecoDoProdutoComNome(String nome){

		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome"; //pode passa ?1 que significa posicao 1  2 abordagem
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("nome",nome) // subsitui o parametro passado no JPQL
				.getSingleResult();//getSingleResult() - um unico registro
	}
	public List<Produto> buscarPorNomeDaCategoria(String nome){

		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome"; //pode passa ?1 que significa posicao 1  2 abordagem
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome",nome) // subsitui o parametro passado no JPQL
				.getResultList();//getResultList() - dispara a query no banco de dados
	}

}
