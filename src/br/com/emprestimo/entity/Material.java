package br.com.emprestimo.entity;

import java.util.Date;

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
@Table(name = "material")
public class Material {

	@Id
	@SequenceGenerator(name = "material_codigo_material_seq", sequenceName = "material_codigo_material_seq")
	@GeneratedValue(generator = "material_codigo_material_seq", strategy = GenerationType.AUTO)
	@Column(name = "codigo_material", nullable = false, unique = true)
	private Integer codigo;
	
	@Column(name = "nome_material")
	private String nomeMaterial;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name="tipo")
	private TipoMaterial tipo;

	public Material() {
		this.dataCadastro = new java.util.Date();
	}
	
	public Material(String nomeMaterial, TipoMaterial tipoMaterial) {
		this.nomeMaterial = nomeMaterial;
		this.tipo = tipoMaterial;
		this.dataCadastro = new java.util.Date();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeMaterial() {
		return nomeMaterial;
	}

	public void setNomeMaterial(String nomeMaterial) {
		this.nomeMaterial = nomeMaterial;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public TipoMaterial getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaterial tipoMaterial) {
		this.tipo = tipoMaterial;
	}

}

