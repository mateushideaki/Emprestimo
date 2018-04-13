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

import br.com.emprestimo.controller.PrincipalWindow;
import br.com.emprestimo.entity.TipoMaterial;

public class ListitemTipo extends Listitem {
	
	private TipoMaterial tipoMaterial;
	
	private Listcell colId = new Listcell();

	private Listcell colNome = new Listcell();
	
	public PrincipalWindow getPrincipalWindow() {
		PrincipalWindow principalWindow = (PrincipalWindow) this.getParent().getParent().getParent().getParent();
		return principalWindow;
	}	
	
	public ListitemTipo(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
		this.colId.setLabel("" + tipoMaterial.getId());
		this.colNome.setLabel(tipoMaterial.getNomeTipo());

		this.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				HashMap<String, TipoMaterial> tipoMaterialMap = new HashMap<String, TipoMaterial>();
				tipoMaterialMap.put("tipoMaterialUpdate", tipoMaterial);
				Component componente = Executions.getCurrent().createComponents("/cadastro-tipo-material.zul", getPrincipalWindow(), tipoMaterialMap);
				if (componente != null) {
					((Window) componente).doModal();
				}
			}
		});

		this.appendChild(colId);
		this.appendChild(colNome);
	}

	public TipoMaterial gettipoMaterial() {
		return tipoMaterial;
	}
}
