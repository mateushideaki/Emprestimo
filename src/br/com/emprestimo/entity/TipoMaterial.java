package br.com.emprestimo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "tipo_material")
public class TipoMaterial {

	@Id
	@SequenceGenerator(name = "tipo_material_id_seq", sequenceName = "tipo_material_id_seq")
	@GeneratedValue(generator = "tipo_material_id_seq", strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "nome_tipo")
	private String nomeTipo;
	
//	@OneToMany(mappedBy = "tipoMaterial", targetEntity = Material.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Material> materiais;

	public TipoMaterial() {

	}
	
	public TipoMaterial(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeTipo() {
		return nomeTipo;
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

//	public List<Material> getMateriais() {
//		return materiais;
//	}
//
//	public void setMateriais(List<Material> materiais) {
//		this.materiais = materiais;
//	}
	
	
}



