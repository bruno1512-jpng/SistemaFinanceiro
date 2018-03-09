package br.com.sxsolucoes.financeiro.beans;



import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import br.com.sxsolucoes.financeiro.DAO.FluxoCaixaDAO;
import br.com.sxsolucoes.financeiro.entity.FluxoCaixa;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import java.util.List;

@ManagedBean(name = "fluxoMB")
@ViewScoped

public class FluxoCaixaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FluxoCaixa fluxo;
	private FluxoCaixa fluxoSelecionado;
	private List<FluxoCaixa> fluxo_lista;
	private FluxoCaixaDAO dao;
	private boolean inserindo, editando;

	private Map fluxoitem = null;
	private ListDataModel model;

	@PostConstruct
	public void init() {
		fluxo = new FluxoCaixa();
	}

	/* Construtor Instancia DAO */
	public FluxoCaixaMB() {
		dao = new FluxoCaixaDAO();
		fluxo_lista = dao.listarTodos();
	}

	


	// será armazenado no selectOneMenu
	public Map getFluxoNome() {
		FluxoCaixaDAO dao = new FluxoCaixaDAO();
		fluxoitem = new LinkedHashMap();
		for (Iterator iter = dao.listarTodos().iterator(); iter.hasNext();) {
			FluxoCaixa f = (FluxoCaixa) iter.next();
			// armazenando os cargos encontrados no bd em um map
			fluxoitem.put(f.getFluxo(), f.getFluxo());
		}
		return fluxoitem;
	}
	

	
	public void prepararNovo() {
		try {
			fluxo = new FluxoCaixa();
			inserindo = true;
			editando = false;
			RequestContext.getCurrentInstance().execute("PF('dlg_NovoFluxo').show()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void prepararEdicao() {
		inserindo = false;
		editando = true;
		RequestContext.getCurrentInstance().execute("PF('dlg_NovoFluxo').show()");
		try {
			fluxo = dao.buscaPorCodigo(fluxoSelecionado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			if (inserindo) {
				dao.gravar(fluxo);
				fluxo_lista = dao.listarTodos();
				fluxo = null;
				// RequestContext.getCurrentInstance().execute("PF('dlg_NovoUsuario').hide()");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso.", null));
			}
			if (editando) {
				dao.alterar(fluxo);
				fluxo_lista = dao.listarTodos();
				fluxo = null;

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Alterados", null));
			}

		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
		inserindo = false;
		editando = false;
		fluxo = null;
	}

	public void cancelar() {
		fluxo = null;
		inserindo = true;
		editando = false;
	}

	public void apagar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			dao.apagar(fluxoSelecionado);
			fluxo_lista = dao.listarTodos();
			fluxo = null;

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Excluido com sucesso..", null));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	public void dialogApagar() {
		RequestContext.getCurrentInstance().execute("PF('dlg_ApagarFluxo').show()");
	}

	public boolean isInserindo() {
		return inserindo;
	}

	public boolean isEditando() {
		return editando;
	}

	/* Getters and Setters */
	public FluxoCaixa getFluxo() {
		return fluxo;
	}

	public void setFluxo(FluxoCaixa fluxo) {
		this.fluxo = fluxo;
	}

	public FluxoCaixa getFluxoSelecionado() {
		return fluxoSelecionado;
	}

	public void setFluxoSelecionado(FluxoCaixa fluxoSelecionado) {
		this.fluxoSelecionado = fluxoSelecionado;
	}

	public List<FluxoCaixa> getFluxo_lista() {
		return fluxo_lista;
	}

	public void setFluxo_lista(List<FluxoCaixa> fluxo_lista) {
		this.fluxo_lista = fluxo_lista;
	}

	public FluxoCaixaDAO getDao() {
		return dao;
	}

	public void setDao(FluxoCaixaDAO dao) {
		this.dao = dao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setInserindo(boolean inserindo) {
		this.inserindo = inserindo;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

}