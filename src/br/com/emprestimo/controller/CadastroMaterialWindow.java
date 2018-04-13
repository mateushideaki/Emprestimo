package br.com.emprestimo.controller;

import java.util.List;
import java.util.Map;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.emprestimo.components.ComboboxTipo;
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

	public ComboboxTipo getComboboxTipo() {
		return (ComboboxTipo) this.getFellow("tipoMaterial");
	}


	public void onCreate(CreateEvent e) {

		Map parametros = e.getArg();
		materialUpdate = (Material) parametros.get("materialUpdate");

		if (materialUpdate != null) {
			getIntCodigo().setValue(materialUpdate.getCodigo());
			getTxtNomeMaterial().setValue(materialUpdate.getNomeMaterial());
			getComboboxTipo().setTipoSelecionado(materialUpdate.getTipo());
		}
		
		TipoMaterialDao tipoDao = new TipoMaterialDao();
		List<TipoMaterial> listaTipos = tipoDao.listtipoMaterials();
		this.getComboboxTipo().carregaLista(listaTipos);
	}

	public void salvarMaterial() {
		TipoMaterialDao tipoDao = new TipoMaterialDao();
		TipoMaterial tipoMaterial = tipoDao.buscaPorId(this.getComboboxTipo().getTipoSelecionado().getId());
		
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

		this.atualizaLista();
		this.detach();

	}
	
	public void atualizaLista() {
		if (this.getWindowPai() != null) {
			List<Material> listaMateriais = dao.listMaterials();
			this.getWindowPai().getListaMateriais().carregaLista(listaMateriais);
		}
	}

	public void cancelar() {

		this.detach();
	}

	public void limpar() {
		this.getIntCodigo().setValue(null);
		this.getTxtNomeMaterial().setValue("");
		this.getComboboxTipo().setSelectedIndex(0);
	}
	
	public void deletarMaterial() {
		MaterialDao dao = new MaterialDao();
		Material material = new Material();
		material.setCodigo(materialUpdate.getCodigo());
		boolean deletou = dao.delete(material.getCodigo());
		if(deletou)
			Messagebox.show("Material " + materialUpdate.getNomeMaterial() + " excluido com sucesso.");
		else
			Messagebox.show("Não foi possível deletar este material, verifique se existem dependências.");
			
		this.atualizaLista();
		this.detach();
	}
}
