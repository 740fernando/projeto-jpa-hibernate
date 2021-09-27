package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	/**
	 * Aparentemente, não precisamos do método merge(), 
	 * porque sua função não é atualizar, mas, sim, para o caso de, 
	 * se por um acaso a entidade estiver detached, o método merge() 
	 * a voltará para o estado managed. Para atualizar no banco de dados, 
	 * vamos: carregar a entidade do banco, mudar o atributo, commitar 
	 * a transação. E, pronto, já está managed.
	 * @param categoria
	 */
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria); // se por acaso sua 
	}
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
}
