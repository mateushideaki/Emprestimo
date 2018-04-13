package br.com.emprestimo.controller;

import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.emprestimo.components.ListboxMaterial;
import br.com.emprestimo.components.ListitemMaterial;
import br.com.emprestimo.dao.EmprestimoDao;
import br.com.emprestimo.dao.MaterialDao;
import br.com.emprestimo.dao.PessoaDao;
import br.com.emprestimo.entity.Emprestimo;
import br.com.emprestimo.entity.Material;
import br.com.emprestimo.entity.Pessoa;

public class EmprestimoWindow extends Window {
	private Pessoa pessoa;
	private PessoaDao pessoaDao = new PessoaDao();

	private Material material;
	private MaterialDao materialDao = new MaterialDao();

	private Emprestimo emprestimo;
	private EmprestimoDao emprestimoDao = new EmprestimoDao();

	public PrincipalWindow getWindowPai() {
		return ((PrincipalWindow) this.getParent());
	}

	// Componentes Pessoa
	public Intbox getIntMatricula() {
		return (Intbox) this.getFellow("matriculaPessoa");
	}

	public Textbox getTxtNome() {
		return (Textbox) this.getFellow("nomePessoa");
	}

	public Textbox getTxtEndereco() {
		return (Textbox) this.getFellow("enderecoPessoa");
	}

	public Textbox getTxtTelefone() {
		return (Textbox) this.getFellow("telefonePessoa");
	}

	// Componentes Material
	public Intbox getIntCodigo() {
		return (Intbox) this.getFellow("codigoMaterial");
	}

	public ListboxMaterial getListMateriais() {
		return (ListboxMaterial) this.getFellow("listaEmprestimo");
	}

	public void onCreate(CreateEvent e) {
		ListboxMaterial listaMateriais = this.getListMateriais();
		listaMateriais.getItems().clear();
	}

	public void buscarPessoa() {
		int matricula = this.getIntMatricula().getValue();
		this.pessoa = pessoaDao.buscaPorMatricula(matricula);
		if (this.pessoa == null) {
			Messagebox.show("Pessoa não foi encontrada.");
			return;
		}

		this.getTxtNome().setValue(this.pessoa.getNome());
		this.getTxtEndereco().setValue(this.pessoa.getEndereco());
		this.getTxtTelefone().setValue(this.pessoa.getTelefone());
	}

	public void adicionarMaterial() {
		int codigo = getIntCodigo().getValue();
		this.material = this.materialDao.buscaPorCodigo(codigo);
		if (this.material == null) {
			Messagebox.show("Material não foi encontrado.");
			return;
		}

		ListitemMaterial itemMaterial = new ListitemMaterial(material, true);
		this.getListMateriais().appendChild(itemMaterial);
	}

	public void efetivarEmprestimo() {
		if (this.pessoa == null) {
			Messagebox.show("Por favor, selecione uma pessoa.");
			return;
		}

		if (this.getListMateriais().getItemCount() == 0) {
			Messagebox.show("Por favor, selecione pelo menos um material.");
			return;
		}

		try {
			for (Material material : this.getListMateriais().getMaterialsLista()) {
				this.emprestimo = new Emprestimo(this.pessoa, material);
				emprestimoDao.save(emprestimo);
			}
			Messagebox.show("Emprestimo cadastrado com sucesso.");
		} catch (Exception ex) {
			Messagebox.show("Erro ao realizar emprestimo: " + ex.getMessage());
		}

		// TipoMaterialDao tipoDao = new TipoMaterialDao();
		// TipoMaterial tipoMaterial =
		// tipoDao.buscaPorId(this.getComboboxTipo().getTipoSelecionado().getId());
		//
		// Material material = new Material();
		// material.setCodigo(this.getIntCodigo().getValue());
		// material.setNomeMaterial(this.getTxtNomeMaterial().getValue());
		// material.setTipo(tipoMaterial);
		//
		// if (material.getCodigo() != null) {
		// dao.atualizar(material, material.getNomeMaterial(), material.getTipo());
		// Messagebox.show("alterado");
		// } else {
		// dao.save(material);
		// Messagebox.show("salvo");
		// }
		//
		// this.atualizaLista();
		// this.detach();

	}

	public void atualizaLista() {
		// if (this.getWindowPai() != null) {
		// List<Material> listaMateriais = dao.listMaterials();
		// this.getWindowPai().getListaMateriais().carregaLista(listaMateriais);
		// }
	}

	public void cancelar() {

		this.detach();
	}

	public void deletarMaterial() {
		// MaterialDao dao = new MaterialDao();
		// Material material = new Material();
		// material.setCodigo(materialUpdate.getCodigo());
		// dao.delete(material.getCodigo());
		// Messagebox.show("Material " + materialUpdate.getNomeMaterial() + " excluido
		// com sucesso.");
		//
		// this.atualizaLista();
		// this.detach();
	}
}
