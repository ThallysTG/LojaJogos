package br.unitins.jogos.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.jogos.model.ItemVenda;
import br.unitins.jogos.model.Venda;

@Named
@ViewScoped
public class DetalheVendaController implements Serializable{

	private static final long serialVersionUID = -6719949836747729139L;

	private Venda venda;
	
	public DetalheVendaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Venda vendaFlash = (Venda) flash.get("vendaFlash");
		if (vendaFlash != null)
			venda = vendaFlash;

	}

	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
			venda.setListaItemVenda(new ArrayList<ItemVenda>());
		}
		return venda;
	}
}