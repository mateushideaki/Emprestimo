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
import org.zkoss.zul.Window;

import br.com.emprestimo.entity.Material;

public class ListitemMaterial extends Listitem {
	private Material material;
	
	private Listcell colCodigo = new Listcell();

	private Listcell colNome = new Listcell();

	private Listcell colData = new Listcell();
	
	private Listcell colTipo = new Listcell();
	
	
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
				Component componente = Executions.getCurrent().createComponents("/cadastro-material.zul", null, materialMap);
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
