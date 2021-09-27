package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
// A funcao dessa classe eh isolar o entitymanager e o entitymanagerfactory
public class JPAUtil {

	// NA JPA, o padr�o de projeto utilizado � o factory.
	// Assim, existe uma factory de EntityManager. Para criar o EntityManager,
	// precisamos do EntityManagerFactory, ele tem o m�todo que faz a constru��o
	// do EntityManager.

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

	// EntityManager = � uma interface, necessario para qlq operacao no banco de
	// dados
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

}
