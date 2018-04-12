package br.com.emprestimo.components;

import java.util.List;

import org.zkoss.zul.Listbox;

import br.com.emprestimo.dao.TipoMaterialDao;
import br.com.emprestimo.entity.TipoMaterial;

public class ListboxTipo extends Listbox {
	private List<TipoMaterial> tiposLista;

	public ListboxTipo() {
		TipoMaterialDao dao = new TipoMaterialDao();
		List<TipoMaterial> tipos = dao.listtipoMaterials();
		this.carregaLista(tipos);
	}

	public ListboxTipo(List<TipoMaterial> tipos){
		this.carregaLista(tipos);
	}

	public void carregaLista(List<TipoMaterial> tipos) {
		this.tiposLista = tipos;
		for (TipoMaterial tipoAtual : tipos) {
			this.appendChild(new ListitemTipo(tipoAtual));
		}
	}

	public void carregaLista() {
		for (TipoMaterial tipoAtual : this.getTiposLista()) {
			this.appendChild(new ListitemTipo(tipoAtual));
		}
	}

	public List<TipoMaterial> getTiposLista() {
		return this.tiposLista;
	}

	public TipoMaterial getPessoaSelecionada() {
		ListitemTipo tipoSelecionado = (ListitemTipo) this.getSelectedItem();
		return tipoSelecionado.gettipoMaterial();
	}	
}
