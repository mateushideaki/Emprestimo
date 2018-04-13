package br.com.emprestimo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

	@Id
	@SequenceGenerator(name = "emprestimo_id_seq", sequenceName = "emprestimo_id_seq")
	@GeneratedValue(generator = "emprestimo_id_seq", strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="pessoa")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name="material")
	private Material material;

	public Emprestimo() {

	}
	
	public Emprestimo(Pessoa pessoa, Material material) {
		this.pessoa = pessoa;
		this.material = material;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}

