package br.unitins.jogos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.jogos.application.Session;
import br.unitins.jogos.application.Util;
import br.unitins.jogos.dao.JogosDAO;
import br.unitins.jogos.model.ItemVenda;
import br.unitins.jogos.model.Jogos;

@Named
@ViewScoped
public class VendaJogosController implements Serializable {

	private static final long serialVersionUID = -8413311224021825448L;

	private String descricao;
	private List<Jogos> listaJogos = null;
	
	public void pesquisar() {
		listaJogos = null;
	}
	
	public void adicionar(int idJogos) {
		JogosDAO dao = new JogosDAO();
		Jogos Jogos = dao.findById(idJogos);
		// verifica se existe um carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona um carrinho (de itens de venda) na sessao
			Session.getInstance().setAttribute("carrinho", 
					new ArrayList<ItemVenda>());
		}
		
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = 
				(ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		
		// criando um item de venda para adicionar no carrinho
		ItemVenda item = new ItemVenda();
		item.setJogos(Jogos);
		item.setValor(Jogos.getPreco());
		
		// adicionando o item no carrinho (variavel local) 
		carrinho.add(item);
		
		// atualizando o carrinho na sessao
		Session.getInstance().setAttribute("carrinho", carrinho);
		
		Util.addInfoMessage("Jogo adicionado no carrinho. "
				+ "Quantidade de Itens: " + carrinho.size());
		
	}

	public List<Jogos> getListaJogoso() {
		if (listaJogos == null) {
			JogosDAO dao = new JogosDAO();
			listaJogos = dao.findByDescricao(getDescricao());
			if (listaJogos == null)
				listaJogos = new ArrayList<Jogos>();
		}
		return listaJogos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}