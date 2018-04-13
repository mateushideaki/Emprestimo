package br.com.emprestimo.components;

import org.zkoss.zul.Comboitem;

import br.com.emprestimo.controller.PrincipalWindow;
import br.com.emprestimo.entity.TipoMaterial;

public class ComboitemTipo extends Comboitem {
	private TipoMaterial tipoMaterial;
	
	public PrincipalWindow getPrincipalWindow() {
		PrincipalWindow principalWindow = (PrincipalWindow) this.getParent().getParent().getParent().getParent();
		return principalWindow;
	}	
	
	public ComboitemTipo(TipoMaterial tipoMaterial) {		
		this.tipoMaterial = tipoMaterial;
		this.setLabel(tipoMaterial.getNomeTipo());
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}
}
