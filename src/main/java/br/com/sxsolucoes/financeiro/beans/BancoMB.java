package br.com.sxsolucoes.financeiro.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import br.com.sxsolucoes.financeiro.DAO.BancoDAO;
import br.com.sxsolucoes.financeiro.DAO.FluxoCaixaDAO;
import br.com.sxsolucoes.financeiro.entity.Banco;
import br.com.sxsolucoes.financeiro.entity.FluxoCaixa;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "bancoMB")
@ViewScoped
public class BancoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Banco banco;
	private Banco bancoSelecionado;
	private List<Banco> banco_lista;
	private BancoDAO dao;
	private boolean inserindo, editando;

	private Map bancoItem = null;
	private Map bancoNumItem = null;

	@PostConstruct
	public void init() {
		banco = new Banco();

	}

	/* Construtor Instancia DAO */
	public BancoMB() {
		dao = new BancoDAO();
		banco_lista = dao.listarTodos();
	}

	/* Capturar o numero do banco */
	public Map getNumBanco() {
		BancoDAO dao = new BancoDAO();
		bancoNumItem = new LinkedHashMap();
		for (Iterator iter = dao.listarTodos().iterator(); iter.hasNext();) {
			Banco f = (Banco) iter.next();
			// armazenando os cargos encontrados no bd em um map
			bancoNumItem.put(f.getNumBanco(), f.getNumBanco());
		}
		return bancoNumItem;
	}

	/* Capturar os nomes dos Bancos */
	public Map getNomeBanco() {
		BancoDAO dao = new BancoDAO();
		bancoItem = new LinkedHashMap();
		for (Iterator iter = dao.listarTodos().iterator(); iter.hasNext();) {
			Banco f = (Banco) iter.next();
			// armazenando os cargos encontrados no bd em um map
			bancoItem.put(f.getBanco(), f.getBanco());
		}
		return bancoItem;
	}


	public void prepararNovo() {
		try {
			banco = new Banco();
			inserindo = true;
			editando = false;
			System.out.println("entrou");
			RequestContext.getCurrentInstance().execute("PF('dlg_NovoBanco').show()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void prepararEdicao() {
		inserindo = false;
		editando = true;
		try {
			banco = dao.buscar(bancoSelecionado);
			RequestContext.getCurrentInstance().execute("PF('dlg_NovoBanco').show()");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			if (inserindo) {
				dao.gravar(banco);
				banco_lista = dao.listarTodos();
				banco = null;

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso.", null));
			}
			if (editando) {
				dao.alterar(banco);
				banco_lista = dao.listarTodos();
				banco = null;

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Alterados", null));
			}

		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
		inserindo = false;
		editando = false;
		banco = null;
	}

	
	public void cancelar() {
		banco = null;
		inserindo = true;
		editando = false;
	}

	
	public void apagar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			dao.apagar(bancoSelecionado);
			banco_lista = dao.listarTodos();
			banco = null;

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Excluido com sucesso..", null));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	
	public void dialogApagar() {
		RequestContext.getCurrentInstance().execute("PF('dlg_ApagarBanco').show()");
	}

	
	public boolean isInserindo() {
		return inserindo;
	}

	
	public boolean isEditando() {
		return editando;
	}

	
	
	/******** GETTERS AND SETTERS ****************/

	public void setInserindo(boolean inserindo) {
		this.inserindo = inserindo;
	}

	public Banco getUsuarioSelecionado() {
		return bancoSelecionado;
	}

	public void setUsuarioSelecionado(Banco bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public Banco getBancoSelecionado() {
		return bancoSelecionado;
	}

	public void setBancoSelecionado(Banco bancoSelecionado) {
		this.bancoSelecionado = bancoSelecionado;
	}

	public List<Banco> getBanco_lista() {
		return banco_lista;
	}

	public void setBanco_lista(List<Banco> banco_lista) {
		this.banco_lista = banco_lista;
	}

	public BancoDAO getDao() {
		return dao;
	}

	public void setDao(BancoDAO dao) {
		this.dao = dao;
	}

	public Map getBancoItem() {
		return bancoItem;
	}

	public void setBancoItem(Map bancoItem) {
		this.bancoItem = bancoItem;
	}

	public Map getBancoNumItem() {
		return bancoNumItem;
	}

	public void setBancoNumItem(Map bancoNumItem) {
		this.bancoNumItem = bancoNumItem;
	}

}
