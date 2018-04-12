package br.com.emprestimo.controller;

import java.util.Map;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.emprestimo.dao.MaterialDao;
import br.com.emprestimo.dao.TipoMaterialDao;
import br.com.emprestimo.entity.Material;
import br.com.emprestimo.entity.TipoMaterial;

public class CadastroMaterialWindow extends Window {
	private Material materialUpdate;
	private MaterialDao dao = new MaterialDao();
	
	public PrincipalWindow getWindowPai() {
		return ((PrincipalWindow) this.getParent());
	}

	public Intbox getIntCodigo() {
		return (Intbox) this.getFellow("codigoMaterial");
	}

	public Textbox getTxtNomeMaterial() {
		return (Textbox) this.getFellow("nomeMaterial");
	}

	public Intbox getIntTipoMaterial() {
		return (Intbox) this.getFellow("tipoMaterial");
	}


	public void onCreate(CreateEvent e) {

		Map parametros = e.getArg();
		materialUpdate = (Material) parametros.get("materialUpdate");

		if (materialUpdate != null) {
			getIntCodigo().setValue(materialUpdate.getCodigo());
			getTxtNomeMaterial().setValue(materialUpdate.getNomeMaterial());
			getIntTipoMaterial().setValue(materialUpdate.getTipo().getId());
		}
	}

	public void salvarMaterial() {
		TipoMaterialDao tipoDao = new TipoMaterialDao();
		TipoMaterial tipoMaterial = tipoDao.buscaPorId(this.getIntTipoMaterial().getValue());
		
		Material material = new Material();
		material.setCodigo(this.getIntCodigo().getValue());
		material.setNomeMaterial(this.getTxtNomeMaterial().getValue());
		material.setTipo(tipoMaterial);

		if (material.getCodigo() != null) {
			dao.atualizar(material, material.getNomeMaterial(), material.getTipo());
			Messagebox.show("alterado");
		} else {
			dao.save(material);
			Messagebox.show("salvo");
		}

		if (this.getWindowPai() != null) {
			dao.listMaterials();
			this.getWindowPai().getListaMateriais().carregaLista();
		}

		this.detach();

	}

	public void cancelar() {

		this.detach();
	}

	public void limpar() {
		this.getIntCodigo().setValue(null);
		this.getTxtNomeMaterial().setValue("");
		this.getIntTipoMaterial().setValue(null);
	}
	
	public void deletarMaterial() {
		MaterialDao dao = new MaterialDao();
		Material material = new Material();
		material.setCodigo(materialUpdate.getCodigo());
		dao.delete(material.getCodigo());
		Messagebox.show("Material " + materialUpdate.getNomeMaterial() + " excluido com sucesso.");
		
		if (this.getWindowPai() != null) {
			dao.listMaterials();
			this.getWindowPai().getListaMateriais().carregaLista();
		}
		
		this.detach();
	}
}