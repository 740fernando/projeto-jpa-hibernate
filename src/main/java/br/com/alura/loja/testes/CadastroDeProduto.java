package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	/**
	  O flush sincroniza as alteracoes com o banco de dados, 
	  porem nao realiza o commit, ou seja, voce poderia continuar 
	  realizando outras operacoes com o EntityManager apos chamar o flush.

      Ja o commit sincroniza as alteracoes com o banco de dados e 
	  "encerra" as operacoes da transacao atual do EntityManager.
	 * @param args
	 */
	public static void main(String[] args) {
		
		cadastrarProduto();
		Long id = 1l;
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		//List<Produto>todos= produtoDao.buscarTodos();
		//List<Produto>todos= produtoDao.buscarPorNome("Xiaomi Redmi");
		List<Produto>todos= produtoDao.buscarPorNomeDaCategoria("Celulares");
		todos.forEach(p2-> System.out.println(p.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPorPrecoDoProdutoComNome("Xiaomi Redmi");
		System.out.println("Preco "+precoDoProduto);
	}

	private static void cadastrarProduto() {
		
		Categoria celulares = new Categoria ("Celulares");
		Produto celular = new Produto("Xiaomi Redmi","Muito legal",new BigDecimal("800.00"),celulares);
		
		//chama o metodo da classe JPAUtil para criar o EntityManager
		EntityManager em = JPAUtil.getEntityManager();
		
		//podemos compartilhar o mesmo EntityManager 
		ProdutoDao produtoDao = new ProdutoDao(em);// cria objeto dao do Tipo ProdutoDAO
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		//Antes de fazer o pesist, temos que chamar em.getTransaction().begin();
		em.getTransaction().begin();//╔ como se dissÚssemos ao JPA e ao EntityManager que pegassem a transašŃo begin() e a iniciasse. 
		
		
		// Estado managed
		categoriaDao.cadastrar(celulares);//metodo persist
		produtoDao.cadastrar(celular);//persist
		em.getTransaction().commit(); //Terminado, temos que commitar essa transašŃo no banco de dados
		
		
//		em.persist(celulares);
//		celulares.setNome("Eletronicos");
//		
//
//		em.flush();
//		em.clear(); // clear() ou close() - coloca todas entidades em estado de "detached"
//		
//		celulares.setNome("123");	// estado detached, e, neste estado, nada que alterarmos na entidade serß sincronizado automaticamente com o banco de dados.		
//		celulares = em.merge(celulares);//merge(celulares) tem como objetivo pegar uma entidade que estß no estado detached e retornß-la uma nova referencia ao estado managed (gerenciado)
//		em.flush();
//		em.clear();
//		//em.remove(celulares);
//		em.flush();
		
		
	//	em.close();//Fechar o entitymanager
	}
}
/*
 * A utilizašŃo do padrŃo Factory Ú ˙til quando vocŕ precisa criar objetos 
 * dinamicamente sem conhecer a classe de implementašŃo, somente sua interface: o 
 * padrŃo factory estabelece uma forma de desenvolver objetos que sŃo responsßveis
 *  pela criašŃo de outros objetos.
 */
