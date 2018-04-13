package br.com.emprestimo.controller;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.emprestimo.components.ListboxEmprestimo;
import br.com.emprestimo.dao.EmprestimoDao;
import br.com.emprestimo.dao.MaterialDao;
import br.com.emprestimo.dao.PessoaDao;
import br.com.emprestimo.entity.Emprestimo;
import br.com.emprestimo.entity.Material;
import br.com.emprestimo.entity.Pessoa;

public class DevolucaoWindow extends Window {
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

	public ListboxEmprestimo getListaEmprestimos() {
		return (ListboxEmprestimo) this.getFellow("listaEmprestimo");
	}

	public void onCreate(CreateEvent e) {
		ListboxEmprestimo listaEmprestimos = this.getListaEmprestimos();
		listaEmprestimos.getItems().clear();
	}

	public void buscarPessoa() {
		try {
			int matricula = this.getIntMatricula().getValue();
			this.pessoa = pessoaDao.buscaPorMatricula(matricula);
			if (this.pessoa == null) {
				Messagebox.show("Pessoa não foi encontrada.");
				return;
			}
			
			this.getTxtNome().setValue(this.pessoa.getNome());
			this.getTxtEndereco().setValue(this.pessoa.getEndereco());
			this.getTxtTelefone().setValue(this.pessoa.getTelefone());
			
			List<Emprestimo> listaEmprestimos = emprestimoDao.listEmprestimos(this.pessoa.getMatricula());
			List<Material> listaMateriaisEmprestimo = new ArrayList<>();
			
			for(Emprestimo emprestimo : listaEmprestimos) {
				listaMateriaisEmprestimo.add(emprestimo.getMaterial());
			}
			
			this.getListaEmprestimos().carregaLista(listaEmprestimos);		
		} catch (Exception ex) {
			Messagebox.show("Digite a matrícula da pessoa.");
		}
		
		
	}

	public void devolverMateriais() {
		if (this.pessoa == null) {
			Messagebox.show("Por favor, selecione uma pessoa.");
			return;
		}

		if (this.getListaEmprestimos().getEmprestimosSelecionados().size() == 0) {
			Messagebox.show("Por favor, selecione pelo menos um empréstimo.");
			return;
		}

		try {
			for (Emprestimo emprestimo : this.getListaEmprestimos().getEmprestimosSelecionados()) {
				emprestimoDao.delete(emprestimo.getId());
			}
			Messagebox.show("Devoluções concluídas.");
		} catch (Exception ex) {
			Messagebox.show("Erro ao realizar devolução: " + ex.getMessage());
		}
		
		this.detach();

	}

}
