package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {

	// método salvar
	public void salvar(Fabricante f) throws SQLException {
		// Juntar String SQL
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");

		// conectar com o banco de dados
		Connection conexao = ConexaoFactory.conectar(); // Vai gerar uma conexao

		// Converter SQL em um comando executável
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		// Definir o valor da "?" no VALUES
		comando.setString(1, f.getDescricao());

		// Executar o comando
		comando.executeUpdate();
	}

	// Método excluir
	public void excluir(Fabricante f) throws SQLException {
		// Definir SQL
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		// Definir o valor da "?"
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		comando.executeUpdate();
	}

	// método editar
	public void editar(Fabricante f) throws SQLException {
		// Definir SQL
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET DESCRICAO = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		// Preparando o comando
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());

		comando.executeUpdate();
	}

	// método Pesquisar
	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {
		// Definir SQL
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		// Executar o comando
		ResultSet resultado = comando.executeQuery();

		// Criar o fabricante a partir do result set
		Fabricante retorno = null;

		// Saber se o código deu certo
		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}

	// Método Listar
	public ArrayList<Fabricante> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}

		return lista;
	}

	// Método buscar por descrição
	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? "); // busca por similaridade
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%"); // Para quandoa procura for com LIKE

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante item = new Fabricante();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}

		return lista;
	}

	// testando a conexao via IDE
	public static void main(String[] args) {
		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("Nike"); Fabricante f2 =
		 * new Fabricante(); f2.setDescricao("Adidas");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.salvar(f1); fdao.salvar(f2);
		 * System.out.println("Os fabricante foram salvos com sucessos"); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar salvar um dos fabricantes");
		 * e.printStackTrace(); }
		 */
		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(1L); // Colocar o L pq é do
		 * tipo Long
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setCodigo(2L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.excluir(f1); fdao.excluir(f2);
		 * System.out.println("Os fabricantes foram excluidos com sucesso!"); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar excluir um dos fabricantes!");
		 * e.printStackTrace(); }
		 */

		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(3L); f1.setDescricao("Pulma");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.editar(f1);
		 * 
		 * System.out.println("fabricantes editados com sucesso"); } catch (SQLException
		 * e) {
		 * System.out.println("Ocorreu um erro ao tentar editar um dos fabricantes!");
		 * e.printStackTrace(); }
		 */
		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(3L);
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setCodigo(6L); // Não tenho o código 6
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { Fabricante f3 = fdao.buscarPorCodigo(f1); Fabricante f4 =
		 * fdao.buscarPorCodigo(f2);
		 * 
		 * System.out.println("Resultado 1: " + f3); System.out.println("Resultado 2: "
		 * + f4); } catch (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar pesquisar os fabricantes!");
		 * e.printStackTrace(); }
		 */

		
		  FabricanteDAO fdao = new FabricanteDAO(); try { ArrayList<Fabricante> lista =
		  fdao.listar();
		  
		  for (Fabricante f : lista) { System.out.println("Resultado: " + f); }
		  
		  } catch (SQLException e) {
		  System.out.println("Ocorreu um erro ao tentar buscar os fabricantes");
		  e.printStackTrace(); }
		 
		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("Adidas");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { ArrayList<Fabricante> lista = fdao.buscarPorDescricao(f1);
		 * 
		 * for (Fabricante f : lista) { System.out.println("Resultado: " + f); } } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar pesquisar o fabricante");
		 * e.printStackTrace(); }
		 */

	}
}
