package br.com.emprestimo.components;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import br.com.emprestimo.controller.PrincipalWindow;
import br.com.emprestimo.entity.Material;

public class ListitemMaterial extends Listitem {
	private Material material;

	private Listcell colCodigo = new Listcell();

	private Listcell colNome = new Listcell();

	private Listcell colData = new Listcell();

	private Listcell colTipo = new Listcell();

	public PrincipalWindow getPrincipalWindow() {
		PrincipalWindow principalWindow = (PrincipalWindow) this.getParent().getParent().getParent().getParent();
		return principalWindow;
	}

	public ListboxMaterial getListParent() {
		return (ListboxMaterial) this.getParent();
	}

	public ListitemMaterial(Material material, boolean telaEmprestimo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		this.material = material;
		this.colCodigo.setLabel("" + material.getCodigo());
		this.colNome.setLabel(material.getNomeMaterial());
		this.colData.setLabel(dateFormat.format(material.getDataCadastro()));
		this.colTipo.setLabel(material.getTipo().getNomeTipo());

		this.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				Messagebox.show("Deseja remover este material da lista?", "Confirmação", Messagebox.YES | Messagebox.NO,
						Messagebox.QUESTION, new EventListener() {
							public void onEvent(Event evt) {
								switch (((Integer) evt.getData()).intValue()) {
								case Messagebox.YES:
									getListParent().removeChild(ListitemMaterial.this);
									break; // the Yes button is pressed
								case Messagebox.NO:
									getListParent().removeChild(ListitemMaterial.this);
									break; // the No button is pressed
								}
							}
						});
			}

		});

		this.appendChild(colCodigo);
		this.appendChild(colNome);
		this.appendChild(colData);
		this.appendChild(colTipo);
	}

	public ListitemMaterial(Material material) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		this.material = material;
		this.colCodigo.setLabel("" + material.getCodigo());
		this.colNome.setLabel(material.getNomeMaterial());
		this.colData.setLabel(dateFormat.format(material.getDataCadastro()));
		this.colTipo.setLabel(material.getTipo().getNomeTipo());

		this.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				HashMap<String, Material> materialMap = new HashMap<String, Material>();
				materialMap.put("materialUpdate", material);
				Component componente = Executions.getCurrent().createComponents("/cadastro-material.zul",
						getPrincipalWindow(), materialMap);
				if (componente != null) {
					((Window) componente).doModal();
				}
			}
		});

		this.appendChild(colCodigo);
		this.appendChild(colNome);
		this.appendChild(colData);
		this.appendChild(colTipo);
	}

	public Material getMaterial() {
		return material;
	}
}
