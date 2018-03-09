package br.com.sxsolucoes.financeiro.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sxsolucoes.financeiro.entity.Usuario;



public class UsuarioDAO {

	EntityManager manager;
	EntityTransaction transaction;

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("financeiro");

	public UsuarioDAO() {

		manager = factory.createEntityManager();

	}

	public void gravar(Usuario usuario) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(usuario);
		manager.flush();
		transaction.commit();
		

	}

	public void update(Usuario usuario) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(usuario);
		transaction.commit();
	}

	public void delete(Usuario usuario) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(usuario);
		transaction.commit();
	}

	public List<Usuario> findAll() {
		return (List<Usuario>) manager.createQuery("select obj from Usuario obj order by obj.id").getResultList();
	}

	/* Filtro Busca por Codigo */
	public Usuario findByCode(Usuario usuario) {
		return manager.find(Usuario.class, usuario.getId());
	}

}