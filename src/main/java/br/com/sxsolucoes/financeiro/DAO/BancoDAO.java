package br.com.sxsolucoes.financeiro.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sxsolucoes.financeiro.entity.Banco;




public class BancoDAO{
	EntityManager manager;
	EntityTransaction transaction;
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("financeiro");

	public BancoDAO() {
		manager = factory.createEntityManager();
	}

	
	public void gravar(Banco banco) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(banco);
		manager.flush();
		transaction.commit();
	}

	public void alterar(Banco banco) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(banco);
		transaction.commit();
	}

	
	public void apagar(Banco banco) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(banco);
		transaction.commit();
	
	}

	public List<Banco> listarTodos() {
		return (List<Banco>) manager.createQuery("select obj from Banco obj order by obj.id").getResultList();
		
	}


	public Banco buscar(Banco banco)  {
		return manager.find(Banco.class, banco.getId());
		
	}
		
}

