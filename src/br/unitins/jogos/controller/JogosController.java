package br.unitins.jogos.controller;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.jogos.dao.JogosDAO;
import br.unitins.jogos.model.Jogos;

@Named
@ViewScoped
public class JogosController extends Controller<Jogos> {

	private static final long serialVersionUID = 1651642114811762868L;
	
	public JogosController() {
		super(new JogosDAO());
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("flashJogos");
		entity = (Jogos) flash.get("flashJogos");
	}
	
	@Override
	public Jogos getEntity() {
		if (entity == null)
			entity = new Jogos();
		return entity;
	}
	
}