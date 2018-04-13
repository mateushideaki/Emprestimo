package br.com.emprestimo.components;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Combobox;

import br.com.emprestimo.dao.TipoMaterialDao;
import br.com.emprestimo.entity.TipoMaterial;

public class ComboboxTipo extends Combobox {
	private List<TipoMaterial> tiposLista;

	public ComboboxTipo() {
		TipoMaterialDao dao = new TipoMaterialDao();
		List<TipoMaterial> tipos = dao.listtipoMaterials();
		this.carregaLista(tipos);
	}

	public ComboboxTipo(List<TipoMaterial> tipos){
		this.carregaLista(tipos);
	}

	public void carregaLista(List<TipoMaterial> tipos) {
		this.tiposLista = tipos;
		this.getChildren().clear();
		for (TipoMaterial tipoAtual : tipos) {
			this.appendChild(new ComboitemTipo(tipoAtual));
		}
	}

	public void carregaLista() {
		this.getChildren().clear();
		for (TipoMaterial tipoAtual : this.getTiposLista()) {
			this.appendChild(new ComboitemTipo(tipoAtual));
		}
	}

	public List<TipoMaterial> getTiposLista() {
		return this.tiposLista;
	}
	
	public void setTipoSelecionado(TipoMaterial tipo) {
		for(Component tipoAtual : this.getChildren()) {
			ComboitemTipo comboitemAtual = (ComboitemTipo) tipoAtual;
			if(comboitemAtual.getTipoMaterial().getId() == tipo.getId())
			{
				this.setSelectedItem(comboitemAtual);
			}
		}
	}

	public TipoMaterial getTipoSelecionado() {
		ComboitemTipo tipoSelecionado = (ComboitemTipo) this.getSelectedItem();
		return tipoSelecionado.getTipoMaterial();
	}	
}
