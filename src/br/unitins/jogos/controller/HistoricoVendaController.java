package br.unitins.jogos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.jogos.application.Session;
import br.unitins.jogos.application.Util;
import br.unitins.jogos.dao.VendaDAO;
import br.unitins.jogos.model.Usuario;
import br.unitins.jogos.model.Venda;

@Named
@ViewScoped
public class HistoricoVendaController implements Serializable {

	private static final long serialVersionUID = -8585030860902916284L;
	
	private List<Venda> listaVenda = null;
	
	/**
	 * Este metodo retorna todas a vendas do usuario logado
	 * 
	 * @return List<Venda>
	 */
	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			VendaDAO dao = new VendaDAO();
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			listaVenda = dao.findByUsuario(usuario.getId());
			if (listaVenda == null)
				listaVenda = new ArrayList<Venda>();
		}
		return listaVenda;
	}
	
	public void detalhes(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("vendaFlash", venda);
		Util.redirect("/LojaJogos/faces/pages/detalhesvenda.xhtml");
	}
	
}