package br.com.emprestimo.components;

import java.text.SimpleDateFormat;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import br.com.emprestimo.controller.PrincipalWindow;
import br.com.emprestimo.entity.Emprestimo;

public class ListitemEmprestimo extends Listitem {
	private Emprestimo emprestimo;

	private Listcell colCodigo = new Listcell();

	private Listcell colMaterial = new Listcell();

	public PrincipalWindow getPrincipalWindow() {
		PrincipalWindow principalWindow = (PrincipalWindow) this.getParent().getParent().getParent().getParent();
		return principalWindow;
	}

	public ListboxEmprestimo getListParent() {
		return (ListboxEmprestimo) this.getParent();
	}

	public ListitemEmprestimo(Emprestimo emprestimo, boolean telaEmprestimo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		this.emprestimo = emprestimo;
		this.colCodigo.setLabel("" + emprestimo.getId());
		this.colMaterial.setLabel(emprestimo.getMaterial().getNomeMaterial());

		this.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show("Deseja remover este emprestimo da lista?", "Confirmação", Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION, new EventListener() {
							public void onEvent(Event evt) {
								switch (((Integer) evt.getData()).intValue()) {
								case Messagebox.YES:
									getListParent().removeChild(ListitemEmprestimo.this);
									break; // the Yes button is pressed
								case Messagebox.NO:
									break; // the No button is pressed
								}
							}
						});
			}

		});

		this.appendChild(colCodigo);
		this.appendChild(colMaterial);
	}

	public ListitemEmprestimo(Emprestimo emprestimo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		this.emprestimo = emprestimo;
		this.colCodigo.setLabel("" + emprestimo.getId());
		this.colMaterial.setLabel(emprestimo.getMaterial().getNomeMaterial());

		this.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
			}
		});

		this.appendChild(colCodigo);
		this.appendChild(colMaterial);
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}
}
