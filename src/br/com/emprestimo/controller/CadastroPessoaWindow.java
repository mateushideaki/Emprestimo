package br.com.emprestimo.controller;

import java.util.List;
import java.util.Map;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.emprestimo.dao.PessoaDao;
import br.com.emprestimo.entity.Pessoa;

public class CadastroPessoaWindow extends Window {

	private Pessoa pessoaUpdate;
	private PessoaDao dao = new PessoaDao();
	
	public PrincipalWindow getWindowPai() {
		return ((PrincipalWindow) this.getParent());
	}

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


	public void onCreate(CreateEvent e) {

		Map parametros = e.getArg();
		pessoaUpdate = (Pessoa) parametros.get("pessoaUpdate");

		if (pessoaUpdate != null) {
			getIntMatricula().setValue(pessoaUpdate.getMatricula());
			getTxtNome().setValue(pessoaUpdate.getNome());
			getTxtEndereco().setValue(pessoaUpdate.getEndereco());
			getTxtTelefone().setValue(pessoaUpdate.getTelefone());
		}
	}

	public void salvarPessoa() {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setMatricula(this.getIntMatricula().getValue());
		pessoa.setNome(this.getTxtNome().getValue());
		pessoa.setEndereco(this.getTxtEndereco().getValue());
		pessoa.setTelefone(this.getTxtTelefone().getValue());

		if (pessoa.getMatricula() != null) {
			dao.atualizar(pessoa, pessoa.getNome(), pessoa.getEndereco(), pessoa.getTelefone());
			Messagebox.show("alterado");
		} else {
			dao.save(pessoa);
			Messagebox.show("salvo");
		}

		if (this.getWindowPai() != null) {
			List<Pessoa> listaPessoas = dao.listPessoas();
			this.getWindowPai().getListaPessoas().carregaLista(listaPessoas);
		}
		
		this.detach();

	}

	public void cancelar() {

		this.detach();
	}

	public void limpar() {
		this.getIntMatricula().setValue(null);
		this.getTxtNome().setValue("");
		this.getTxtEndereco().setValue("");
		this.getTxtTelefone().setValue("");
	}
	
	public void deletarPessoa() {
		PessoaDao dao = new PessoaDao();
		Pessoa pessoa = new Pessoa();
		pessoa.setMatricula(pessoaUpdate.getMatricula());
		dao.delete(pessoa.getMatricula());
		Messagebox.show("Pessoa " + pessoaUpdate.getNome() + " excluida com sucesso.");
		
		if (this.getWindowPai() != null) {
			List<Pessoa> listaPessoas = dao.listPessoas();
			this.getWindowPai().getListaPessoas().carregaLista(listaPessoas);
		}
		
		this.detach();
	}
}
