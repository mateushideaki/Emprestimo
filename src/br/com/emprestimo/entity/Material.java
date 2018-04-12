//package br.com.emprestimo.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "material")
//public class Material {
//
//	@Id
//	@SequenceGenerator(name = "material_codigo_material_seq", sequenceName = "material_codigo_material_seq")
//	@GeneratedValue(generator = "material_codigo_material_seq", strategy = GenerationType.AUTO)
//	@Column(name = "codigo", nullable = false, unique = true)
//	private Integer codigo;
//	@Column(name = "nome")
//	private String nome;
//	@Column(name = "endereco")
//	private String endereco;
//	@Column(name = "telefone")
//	private String telefone;
//
//	public Material() {
//
//	}
//	
//	public Pessoa(String nome, String endereco, String telefone) {
//		this.nome = nome;
//		this.endereco = endereco;
//		this.telefone = telefone;
//	}
//
//	public Integer getMatricula() {
//		return matricula;
//	}
//
//	public void setMatricula(Integer matricula) {
//		this.matricula = matricula;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getEndereco() {
//		return endereco;
//	}
//
//	public void setEndereco(String endereco) {
//		this.endereco = endereco;
//	}
//
//	public String getTelefone() {
//		return telefone;
//	}
//
//	public void setTelefone(String telefone) {
//		this.telefone = telefone;
//	}
//
//}
//
