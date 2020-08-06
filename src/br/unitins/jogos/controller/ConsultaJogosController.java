package br.unitins.jogos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.jogos.dao.JogosDAO;
import br.unitins.jogos.model.Jogos;

@Named
@ViewScoped
public class ConsultaJogosController implements Serializable{
	
	private static final long serialVersionUID = 5971844866316069324L;
	
	private int tipoFiltro = 1;
	private String filtro;
	private List<Jogos> listaJogos;
	
	public void pesquisar() {
		JogosDAO dao = new JogosDAO();
		if (tipoFiltro == 1)
			listaJogos = dao.findByDescricao(getFiltro());
		else 
			listaJogos = dao.findByConsole(getFiltro());
	}
	
	public String novoJogos() {
		return "jogos.xhtml?faces-redirect=true";
	}
	
	public String editar(Jogos jogos) {
		JogosDAO dao = new JogosDAO();
		jogos = dao.findById(jogos.getId());
		
		Flash flash = FacesContext.getCurrentInstance().
						getExternalContext().getFlash();
		
		flash.put("flashJogos", jogos);
		return "jogos.xhtml?faces-redirect=true";
	}

	public List<Jogos> getListaJogos() {
		if (listaJogos == null) {
			listaJogos = new ArrayList<Jogos>();
		}
		return listaJogos;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

}