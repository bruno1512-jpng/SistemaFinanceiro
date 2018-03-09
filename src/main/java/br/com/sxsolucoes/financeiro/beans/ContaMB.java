package br.com.sxsolucoes.financeiro.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import br.com.sxsolucoes.financeiro.DAO.ContaDAO;
import br.com.sxsolucoes.financeiro.DAO.FluxoCaixaDAO;
import br.com.sxsolucoes.financeiro.entity.Conta;
import br.com.sxsolucoes.financeiro.entity.FluxoCaixa;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "contaMB")
@ViewScoped
public class ContaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Conta conta;
	private Conta contaSelecionado;
	private List<Conta> conta_lista;
	private ContaDAO dao;
	private boolean inserindo, editando;

	private String numeroBanco;

	private Map contaNumItem = null;

	@PostConstruct
	public void init() {
		conta = new Conta();

	}

	/* Construtor Instancia DAO */
	public ContaMB() {
		dao = new ContaDAO();
		conta_lista = dao.listarTodos();

	}

	/* Captusrar o numero do conta */
	public Map getNomeConta() {
		ContaDAO dao = new ContaDAO();
		contaNumItem = new LinkedHashMap();
		for (Iterator iter = dao.listarTodos().iterator(); iter.hasNext();) {
			Conta f = (Conta) iter.next();
			// armazenando os cargos encontrados no bd em um map
			contaNumItem.put(f.getNumConta(), f.getNumConta());
		}
		return contaNumItem;
	}

	public void prepararNovo() {
		try {
			conta = new Conta();
			inserindo = true;
			editando = false;
			System.out.println("entrou");
			RequestContext.getCurrentInstance().execute("PF('dlg_NovaConta').show()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void prepararEdicao() {
		inserindo = false;
		editando = true;
		try {
			conta = dao.buscar(contaSelecionado);
			RequestContext.getCurrentInstance().execute("PF('dlg_NovaConta').show()");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			if (inserindo) {
				dao.gravar(conta);
				conta_lista = dao.listarTodos();
				conta = null;

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso.", null));
			}
			if (editando) {
				dao.alterar(conta);
				conta_lista = dao.listarTodos();
				conta = null;

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Alterados", null));
			}

		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
		inserindo = false;
		editando = false;
		conta = null;
	}

	public void cancelar() {
		conta = null;
		inserindo = true;
		editando = false;
	}

	public void apagar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			dao.apagar(contaSelecionado);
			conta_lista = dao.listarTodos();
			conta = null;

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Excluido com sucesso..", null));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	public void dialogApagar() {
		RequestContext.getCurrentInstance().execute("PF('dlg_ApagarConta').show()");
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

	public Conta getUsuarioSelecionado() {
		return contaSelecionado;
	}

	public void setUsuarioSelecionado(Conta contaSelecionado) {
		this.contaSelecionado = contaSelecionado;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getContaSelecionado() {
		return contaSelecionado;
	}

	public void setContaSelecionado(Conta contaSelecionado) {
		this.contaSelecionado = contaSelecionado;
	}

	public List<Conta> getConta_lista() {
		return conta_lista;
	}

	public void setConta_lista(List<Conta> conta_lista) {
		this.conta_lista = conta_lista;
	}

	public ContaDAO getDao() {
		return dao;
	}

	public void setDao(ContaDAO dao) {
		this.dao = dao;
	}

}
