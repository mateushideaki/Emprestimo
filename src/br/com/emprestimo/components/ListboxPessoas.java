package br.com.emprestimo.components;

import java.util.List;
import org.zkoss.zul.Listbox;

import br.com.emprestimo.dao.PessoaDao;
import br.com.emprestimo.entity.Pessoa;


public class ListboxPessoas extends Listbox {
	
	private List<Pessoa> pessoasLista;

	public ListboxPessoas() {
		PessoaDao dao = new PessoaDao();
		List<Pessoa> pessoas = dao.listPessoas();
		this.carregaLista(pessoas);
	}

	public ListboxPessoas(List<Pessoa> pessoas){
		this.carregaLista(pessoas);
	}

	public void carregaLista(List<Pessoa> pessoas) {
		this.pessoasLista = pessoas;
		this.getChildren().clear();
		for (Pessoa pessoaAtual : pessoas) {
			this.appendChild(new ListitemPessoa(pessoaAtual));
		}
	}

	public void carregaLista() {
		this.getChildren().clear();
		for (Pessoa pessoaAtual : this.getPessoasLista()) {
			this.appendChild(new ListitemPessoa(pessoaAtual));
		}
	}

	public List<Pessoa> getPessoasLista() {
		return this.pessoasLista;
	}

	public Pessoa getPessoaSelecionada() {
		ListitemPessoa pessoaSelecionada = (ListitemPessoa) this.getSelectedItem();
		return pessoaSelecionada.getPessoa();
	}	


}
