package br.com.sxsolucoes.financeiro.beans;



import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import br.com.sxsolucoes.financeiro.DAO.UsuarioDAO;
import br.com.sxsolucoes.financeiro.entity.Usuario;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


import java.util.List;

@ManagedBean(name = "usuarioMB")
@ViewScoped

public class UsuarioMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Usuario usuarioSelecionado;
	private List<Usuario> usuario_lista;
	private UsuarioDAO dao;
	private boolean inserindo, editando;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	
	}

	/* Construtor Instancia DAO */
	public UsuarioMB() {
		dao = new UsuarioDAO();
		usuario_lista = dao.findAll();
		
	}

	
	
	public void prepararNovo() {
		try {
			usuario = new Usuario();
			inserindo = true;
			editando = false;
			
			RequestContext.getCurrentInstance().execute("PF('dlg_NovoUsuario').show()");
			System.out.println("depois do chamado >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void prepararEdicao() {
		inserindo = false;
		editando = true;
		RequestContext.getCurrentInstance().execute("PF('dlg_NovoUsuario').show()");
		try {
			usuario = dao.findByCode(usuarioSelecionado);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			if (inserindo == true) {System.out.println("pronto pra inserir");}
			if (inserindo) {
				
				
				dao.gravar(usuario);
				System.out.println("depois de gravar >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				usuario_lista = dao.findAll();
				usuario = null;
				//RequestContext.getCurrentInstance().execute("PF('dlg_NovoUsuario').hide()");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso.", null));
			}
			if (editando) {
				dao.update(usuario);
				usuario_lista = dao.findAll();
				usuario = null;

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados Alterados", null));
			}

		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
		inserindo = false;
		editando = false;
		usuario = null;
	}

	public void cancelar() {
		usuario = null;
		inserindo = true;
		editando = false;
	}

	public void apagar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			dao.delete(usuarioSelecionado);
			usuario_lista = dao.findAll();
			usuario = null;

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Excluido com sucesso..", null));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	public void dialogApagar() {
		RequestContext.getCurrentInstance().execute("PF('dlg_ApagarUsuario').show()");
	}

	public boolean isInserindo() {
		return inserindo;
	}

	public boolean isEditando() {
		return editando;
	}

	/******** GETTERS AND SETTERS ****************/
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<Usuario> getUsuario_lista() {
		return usuario_lista;
	}

	public void setUsuario_lista(List<Usuario> usuario_lista) {
		this.usuario_lista = usuario_lista;
	}

	public UsuarioDAO getDao() {
		return dao;
	}

	public void setDao(UsuarioDAO dao) {
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
