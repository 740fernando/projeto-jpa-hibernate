package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue, isto é, para dizer como o valor da chave primária é gerado.
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadatro = LocalDate.now();
	
	//@ManyToOne = Ou seja, muitos produtos estão vinculados com uma Categoria. Uma categoria pode ter vários produtos, mas o produto tem uma única categoria.
	@ManyToOne
	private Categoria categoria;
	// @Enumerated(EnumType.STRING)- para que ele cadastre o nome da constante no banco de dados, não a ordem
	
	
	public Produto() {
		
	}
	
	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	public LocalDate getDataCadatro() {
		return dataCadatro;
	}
	public void setDataCadatro(LocalDate dataCadatro) {
		this.dataCadatro = dataCadatro;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
/**
 * Existem algumas anotações da JPA para relacionamento, 
 * o @ManyToOne, @OneToMany (que é o contrário), @OneToOne e @MayToMany.
 *  A escolha dependerá da cardinalidade, do tipo de relacionamento entre 
 *  as tabelas. Agora, no CadastroDeProduto.java está dando um erro em 
 *  Categoria.CELULARES, porque Categoria não é mais um enum. Precisamos de uma categoria 
 * cadastrada no banco de dados para associá-la com esse produto.
 */
