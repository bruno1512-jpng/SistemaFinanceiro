package br.com.sxsolucoes.financeiro.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sxsolucoes.financeiro.entity.Conta;



public class ContaDAO{
	EntityManager manager;
	EntityTransaction transaction;
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("financeiro");

	public ContaDAO() {
		manager = factory.createEntityManager();
	}

	

	public void gravar(Conta conta) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(conta);
		manager.flush();
		transaction.commit();
	}

	public void alterar(Conta conta) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(conta);
		transaction.commit();
	}

	

	public void apagar(Conta conta)  {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(conta);
		transaction.commit();
	
	}

	public List<Conta> listarTodos()  {
		return (List<Conta>) manager.createQuery("select obj from Conta obj order by obj.id").getResultList();
		
	}


	public Conta buscar(Conta conta) {
		return manager.find(Conta.class, conta.getId());
		
	}
		
}

