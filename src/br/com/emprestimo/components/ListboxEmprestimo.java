package br.com.emprestimo.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import br.com.emprestimo.dao.EmprestimoDao;
import br.com.emprestimo.entity.Emprestimo;

public class ListboxEmprestimo extends Listbox {
	private List<Emprestimo> emprestimosLista;

	public ListboxEmprestimo() {
		EmprestimoDao dao = new EmprestimoDao();
		List<Emprestimo> emprestimos = dao.listEmprestimos();
		this.carregaLista(emprestimos);
	}

	public ListboxEmprestimo(List<Emprestimo> emprestimos){
		this.carregaLista(emprestimos);
	}

	public void carregaLista(List<Emprestimo> emprestimos) {
		this.emprestimosLista = emprestimos;
		this.getItems().clear();
		for (Emprestimo emprestimoAtual : emprestimos) {
			this.appendChild(new ListitemEmprestimo(emprestimoAtual, true));
		}
	}

	public void carregaLista() {
		this.getItems().clear();
		for (Emprestimo emprestimoAtual : this.getEmprestimosLista()) {
			this.appendChild(new ListitemEmprestimo(emprestimoAtual));
		}
	}

	public void clearList() {
		this.emprestimosLista = new ArrayList<>();
		this.getItems().clear();
	}

	public void addEmprestimo(Emprestimo emprestimo) {
		this.emprestimosLista.add(emprestimo);
		this.appendChild(new ListitemEmprestimo(emprestimo, true));
	}

	public List<Emprestimo> getEmprestimosLista() {
		return this.emprestimosLista;
	}
	
	public List<Emprestimo> getEmprestimosSelecionados() {
		Set<Listitem> listaItensSelecionados = this.getSelectedItems();
		List<Emprestimo> listaEmprestimosSelecionados = new ArrayList<>();
		
		for(Listitem itemEmprestimo : listaItensSelecionados) {
			ListitemEmprestimo liEmprestimo = (ListitemEmprestimo) itemEmprestimo;
			Emprestimo emprestimo = liEmprestimo.getEmprestimo();
			listaEmprestimosSelecionados.add(emprestimo);
		}
		
		return listaEmprestimosSelecionados;
	}

	public Emprestimo getEmprestimoSelecionada() {
		ListitemEmprestimo emprestimoSelecionado = (ListitemEmprestimo) this.getSelectedItem();
		return emprestimoSelecionado.getEmprestimo();
	}
}
