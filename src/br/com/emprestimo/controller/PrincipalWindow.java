	package br.com.emprestimo.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.com.emprestimo.components.ListboxMaterial;
import br.com.emprestimo.components.ListboxPessoas;
import br.com.emprestimo.components.ListboxTipo;

public class PrincipalWindow extends Window {
	
	public ListboxPessoas getListaPessoas() {
		return (ListboxPessoas) this.getFellow("listaPessoas");
	}
	
	public ListboxMaterial getListaMateriais() {
		return (ListboxMaterial) this.getFellow("listaMateriais");
	}
	
	public ListboxTipo getListaTipos() {
		return (ListboxTipo) this.getFellow("listaTipos");
	}

	public void abreTelaCadastroPessoas() {
		Component componente = Executions.getCurrent().createComponents("/cadastro-pessoa.zul", this, null);
		if (componente != null) {
			((Window) componente).doModal();
		}
	}
	
	public void abreTelaCadastroTipo() {
		Component componente = Executions.getCurrent().createComponents("/cadastro-tipo-material.zul", this, null);
		if (componente != null) {
			((Window) componente).doModal();
		}
	}
	
	public void abreTelaCadastroMaterial() {
		Component componente = Executions.getCurrent().createComponents("/cadastro-material.zul", this, null);
		if (componente != null) {
			((Window) componente).doModal();
		}
	}
    
    public void cancelar() {
    	this.detach();
    }


}
