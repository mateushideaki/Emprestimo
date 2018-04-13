package br.com.emprestimo.controller;

import java.util.List;
import java.util.Map;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.emprestimo.dao.TipoMaterialDao;
import br.com.emprestimo.entity.TipoMaterial;

public class CadastroTipoMaterialWindow extends Window {
	private TipoMaterial tipoMaterialUpdate;
	private TipoMaterialDao dao = new TipoMaterialDao();
	
	public PrincipalWindow getWindowPai() {
		return ((PrincipalWindow) this.getParent());
	}

	public Intbox getIntId() {
		return (Intbox) this.getFellow("idTipo");
	}

	public Textbox getTxtNomeTipo() {
		return (Textbox) this.getFellow("tipoMaterial");
	}


	public void onCreate(CreateEvent e) {

		Map parametros = e.getArg();
		tipoMaterialUpdate = (TipoMaterial) parametros.get("tipoMaterialUpdate");

		if (tipoMaterialUpdate != null) {
			getIntId().setValue(tipoMaterialUpdate.getId());
			getTxtNomeTipo().setValue(tipoMaterialUpdate.getNomeTipo());
		}
	}

	public void salvarTipoMaterial() {
		
		TipoMaterial tipoMaterial = new TipoMaterial();
		tipoMaterial.setId(this.getIntId().getValue());
		tipoMaterial.setNomeTipo(this.getTxtNomeTipo().getValue());

		if (tipoMaterial.getId() != null) {
			dao.atualizar(tipoMaterial, tipoMaterial.getNomeTipo());
			Messagebox.show("alterado");
		} else {
			dao.save(tipoMaterial);
			Messagebox.show("salvo");
		}


		atualizaLista();
		this.detach();

	}

	public void atualizaLista() {
		if (this.getWindowPai() != null) {
			List<TipoMaterial> listaTipos = dao.listtipoMaterials();
			this.getWindowPai().getListaTipos().carregaLista(listaTipos);
		}
	}
	
	public void cancelar() {

		this.detach();
	}

	public void limpar() {
		this.getIntId().setValue(null);
		this.getTxtNomeTipo().setValue("");
	}
	
	public void deletarTipoMaterial() {
		TipoMaterialDao dao = new TipoMaterialDao();
		TipoMaterial tipoMaterial = new TipoMaterial();
		tipoMaterial.setId(tipoMaterialUpdate.getId());
		dao.delete(tipoMaterial.getId());
		Messagebox.show("Tipo Material " + tipoMaterialUpdate.getNomeTipo() + " excluido com sucesso.");

		this.atualizaLista();
		this.detach();
	}
}
