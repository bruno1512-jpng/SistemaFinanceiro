package br.com.sxsolucoes.financeiro.beans;



import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import br.com.sxsolucoes.financeiro.DAO.CaixaDAO;
import br.com.sxsolucoes.financeiro.entity.Caixa;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@ManagedBean(name = "caixaMB")
@ViewScoped
public class CaixaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Caixa caixa;
	private Caixa caixaSelecionado;
	private List<Caixa> caixa_lista;
	private CaixaDAO dao;
	private boolean inserindo, editando;
	private double saldo_total;

	@PostConstruct
	public void init() {
		caixa = new Caixa();
		caixa.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	}

	/* Calcula Saldo */
	public void calculaSaldo() {
		double saldo_anterior = 0;
		for (Caixa caixa : caixa_lista) {
			/* Verifica se é Debito ou Credito e executa a acao */
			if (caixa.getTipo().equalsIgnoreCase("C")) {
				saldo_anterior = saldo_anterior + caixa.getValor();
			} else {
				if (caixa.getTipo().equalsIgnoreCase("D")) {
					saldo_anterior = saldo_anterior - caixa.getValor();
				}
			}
			caixa.setSaldo(saldo_anterior); // salva o valor no campo saldo.
			setSaldo_total(saldo_anterior);// salva o ultimo saldo anterior no campo saldo_total
		}
	}

	/* Construtor Instancia DAO */
	public CaixaMB() {
		dao = new CaixaDAO();
		caixa_lista = dao.findAll();
		calculaSaldo();
	}

	public void prepararNovo() {
		try {
			caixa = new Caixa();
			caixa.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			inserindo = true;
			editando = false;
			RequestContext.getCurrentInstance().execute("PF('dlg_NovoMovimento').show()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void prepararEdicao() {
		inserindo = false;
		editando = true;
		try {
			caixa = dao.findByCode(caixaSelecionado);
			RequestContext.getCurrentInstance().execute("PF('dlg_NovoMovimento').show()");
			calculaSaldo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			if (inserindo) {
				dao.gravar(caixa);
				caixa_lista = dao.findAll();
				caixa = null;
				calculaSaldo();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso.", null));
			}
			if (editando) {
				dao.update(caixa);
				caixa_lista = dao.findAll();
				caixa = null;
				calculaSaldo();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Alterados", null));
			}

		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
		inserindo = false;
		editando = false;
		caixa = null;
	}

	public void cancelar() {
		caixa = null;
		inserindo = true;
		editando = false;
	}

	public void apagar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			dao.delete(caixaSelecionado);
			caixa_lista = dao.findAll();
			caixa = null;
			calculaSaldo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Excluido com sucesso..", null));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	public void dialogApagar() {
		RequestContext.getCurrentInstance().execute("PF('dlg_ApagarCaixa').show()");
	}

	public boolean isInserindo() {
		return inserindo;
	}

	public boolean isEditando() {
		return editando;
	}


	/******** GETTERS AND SETTERS ****************/
	
	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Caixa getCaixaSelecionado() {
		return caixaSelecionado;
	}

	public void setCaixaSelecionado(Caixa caixaSelecionado) {
		this.caixaSelecionado = caixaSelecionado;
	}

	public List<Caixa> getCaixa_lista() {
		return caixa_lista;
	}

	public void setCaixa_lista(List<Caixa> caixa_lista) {
		this.caixa_lista = caixa_lista;
	}

	public CaixaDAO getDao() {
		return dao;
	}

	public void setDao(CaixaDAO dao) {
		this.dao = dao;
	}

	public double getSaldo_total() {
		return saldo_total;
	}

	public void setSaldo_total(double saldo_total) {
		this.saldo_total = saldo_total;
	}

	public void setInserindo(boolean inserindo) {
		this.inserindo = inserindo;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}




}