package br.com.emprestimo.components;

import java.util.List;

import org.zkoss.zul.Listbox;

import br.com.emprestimo.dao.MaterialDao;
import br.com.emprestimo.entity.Material;

public class ListboxMaterial extends Listbox {
	private List<Material> materiaisLista;

	public ListboxMaterial() {
		MaterialDao dao = new MaterialDao();
		List<Material> materiais = dao.listMaterials();
		this.carregaLista(materiais);
	}

	public ListboxMaterial(List<Material> materiais){
		this.carregaLista(materiais);
	}

	public void carregaLista(List<Material> materiais) {
		this.materiaisLista = materiais;
		for (Material materialAtual : materiais) {
			this.appendChild(new ListitemMaterial(materialAtual));
		}
	}

	public void carregaLista() {
		for (Material materialAtual : this.getMaterialsLista()) {
			this.appendChild(new ListitemMaterial(materialAtual));
		}
	}

	public List<Material> getMaterialsLista() {
		return this.materiaisLista;
	}

	public Material getMaterialSelecionada() {
		ListitemMaterial materialSelecionado = (ListitemMaterial) this.getSelectedItem();
		return materialSelecionado.getMaterial();
	}	

}
