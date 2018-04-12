package br.com.emprestimo.controller;

import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.com.emprestimo.components.ListboxPessoas;
import br.com.emprestimo.dao.PessoaDao;
import br.com.emprestimo.entity.Pessoa;

public class PrincipalWindow extends Window {
	
	public ListboxPessoas getListaPessoas() {
		return (ListboxPessoas) this.getFellow("listaPessoas");
	}
	
	public ListboxPessoas getListaTipos() {
		return (ListboxPessoas) this.getFellow("listaTipos");
	}

	public void abreTelaCadastroPessoas() {
		Component componente = Executions.getCurrent().createComponents("/cadastro-pessoa.zul", null, null);
		if (componente != null) {
			((Window) componente).doModal();
		}
	}
	
	public void abreTelaCadastroTipo() {
		Component componente = Executions.getCurrent().createComponents("/cadastro-tipo-material.zul", null, null);
		if (componente != null) {
			((Window) componente).doModal();
		}
	}
    
    public void cancelar() {
    	this.detach();
    }


}
