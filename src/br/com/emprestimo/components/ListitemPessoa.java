package br.com.emprestimo.components;

import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import br.com.emprestimo.entity.Pessoa;

public class ListitemPessoa extends Listitem {
	
	private Pessoa pessoa;
	
	private Listcell colMatricula = new Listcell();

	private Listcell colNome = new Listcell();

	private Listcell colEndereco = new Listcell();
	
	private Listcell colTelefone = new Listcell();
	
	public ListitemPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		this.colMatricula.setLabel("" + pessoa.getMatricula());
		this.colNome.setLabel(pessoa.getNome());
		this.colEndereco.setLabel(pessoa.getEndereco());
		this.colTelefone.setLabel(pessoa.getTelefone());

		this.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				HashMap<String, Pessoa> pessoaMap = new HashMap<String, Pessoa>();
				pessoaMap.put("pessoaUpdate", pessoa);
				Component componente = Executions.getCurrent().createComponents("/cadastro-pessoa.zul", null, pessoaMap);
				if (componente != null) {
					((Window) componente).doModal();
				}
			}
		});

		this.appendChild(colMatricula);
		this.appendChild(colNome);
		this.appendChild(colEndereco);
		this.appendChild(colTelefone);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
}
