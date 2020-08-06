package br.unitins.jogos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.jogos.model.Jogos;

public class JogosDAO extends DAO<Jogos> {
	
	public boolean create (Jogos jogos) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO jogos ");
		sql.append("	(descricao, console, preco, estoque) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ? , ? ) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, jogos.getDescricao());
			stat.setString(2, jogos.getConsole());
			if (jogos.getPreco() != null)
				stat.setFloat(3, jogos.getPreco());
			else
				stat.setNull(3, java.sql.Types.FLOAT);
			
			if (jogos.getEstoque() != null)
				stat.setInt(4, jogos.getEstoque());
			else
				stat.setNull(4, java.sql.Types.INTEGER);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclusão realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public boolean update(Jogos jogos) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE jogos ");
		sql.append("	SET descricao=?, console=?, preco=?, estoque=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, jogos.getDescricao());
			stat.setString(2, jogos.getConsole());
			stat.setFloat(3, jogos.getPreco());
			stat.setInt(4, jogos.getEstoque());
			stat.setInt(5, jogos.getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Alteração realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;		
		
	}

	public boolean delete(int id) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM jogos ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Remoção realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public List<Jogos> findAll() {
		List<Jogos> listaJogos = new ArrayList<Jogos>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, console, preco, estoque ");
		sql.append("FROM ");
		sql.append("	jogos ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stat.executeQuery();
			
			Jogos jogos = null;
			
			while(rs.next()) {
				jogos = new Jogos();
				jogos.setId(rs.getInt("id"));
				jogos.setDescricao(rs.getString("descricao"));
				jogos.setConsole(rs.getString("console"));
				jogos.setPreco(rs.getFloat("preco"));
				jogos.setEstoque(rs.getInt("estoque"));
				listaJogos.add(jogos);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaJogos;
	}

	public List<Jogos> findByDescricao(String descricao) {
		List<Jogos> listaJogos = new ArrayList<Jogos>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, console, preco, estoque ");
		sql.append("FROM ");
		sql.append("	jogos ");
		sql.append("WHERE ");
		sql.append("	descricao ilike ? ");
		sql.append("ORDER BY descricao ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + descricao  + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Jogos jogos = null;
			
			while(rs.next()) {
				jogos = new Jogos();
				jogos.setId(rs.getInt("id"));
				jogos.setDescricao(rs.getString("descricao"));
				jogos.setConsole(rs.getString("console"));
				jogos.setPreco(rs.getFloat("preco"));
				jogos.setEstoque(rs.getInt("estoque"));
				listaJogos.add(jogos);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaJogos;
	}	
	
	public List<Jogos> findByConsole(String console) {
		List<Jogos> listaJogos = new ArrayList<Jogos>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, console, preco, estoque ");
		sql.append("FROM ");
		sql.append("	jogos ");
		sql.append("WHERE ");
		sql.append("	console ilike ? ");
		sql.append("ORDER BY console ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + console  + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Jogos jogos = null;
			
			while(rs.next()) {
				jogos = new Jogos();
				jogos.setId(rs.getInt("id"));
				jogos.setDescricao(rs.getString("descricao"));
				jogos.setConsole(rs.getString("console"));
				jogos.setPreco(rs.getFloat("preco"));
				jogos.setEstoque(rs.getInt("estoque"));
				listaJogos.add(jogos);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaJogos;
	}	
	
	public Jogos findById(int id) {
		Jogos jogos = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, console, preco, estoque ");
		sql.append("FROM ");
		sql.append("	jogos ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				jogos = new Jogos();
				jogos.setId(rs.getInt("id"));
				jogos.setDescricao(rs.getString("descricao"));
				jogos.setConsole(rs.getString("console"));
				jogos.setPreco(rs.getFloat("preco"));
				jogos.setEstoque(rs.getInt("estoque"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return jogos;
	}

}