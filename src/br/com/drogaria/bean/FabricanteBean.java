package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private Fabricante fabricante; // Vari�vel para guardar os dados que quero salvar, excluir ou editar
	private ArrayList<Fabricante> itens; //Para representar os itens do dataTable
	private ArrayList<Fabricante> itensFiltrados; //Para itens selecionados

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	

	// M�todo ir� ser inicializado antes da p�gina ser carregado
	@PostConstruct
	public void prepararPesquisa() {
		try {
			// Criar um dado
			FabricanteDAO dao = new FabricanteDAO();

			// Relizar a pesquisa
			itens = dao.listar();

		} catch (SQLException ex) {
			ex.printStackTrace();

			// adicionar mensagem de erro
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// Instanciar um fabricante para gravar um novo Fabricante
	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	// m�todo para invocar o Banco de Dados
	public void novo() {
		try {
			// Criar um dado
			FabricanteDAO dao = new FabricanteDAO();

			// Realizar o salvar
			dao.salvar(fabricante);

			// Recarregar os dados do ListaDataModel
			itens = dao.listar();
			
			// adicionar mensagem de sucesso
			JSFUtil.adicionarMensagemSucesso("Novo fabricante cadastrado com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();

			// adicionar mensagem de erro
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// m�todo de exclus�o
	public void excluir() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricante);
			// refresh na tabela
			itens = dao.listar();;

			// Adicionar mensagem de sucesso
			JSFUtil.adicionarMensagemSucesso("Exclus�o feita com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// M�todo de Editar
	public void editar() {
		try {
			// Criar DAO
			FabricanteDAO dao = new FabricanteDAO();

			// Chamar o editar
			dao.editar(fabricante);

			// refresh na tabela
			itens = dao.listar();

			// Mensagem de sucesso
			JSFUtil.adicionarMensagemSucesso("Edi��o feita com sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
