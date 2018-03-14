package br.com.sxsolucoes.financeiro.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sxsolucoes.financeiro.entity.Caixa;

public class CaixaDAO {

	EntityManager manager;
	EntityTransaction transaction;

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("financeiro");

	public CaixaDAO() {

		manager = factory.createEntityManager();

	}

	public void gravar(Caixa caixa) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(caixa);
		manager.flush();
		transaction.commit();

	}

	public void update(Caixa caixa) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(caixa);
		transaction.commit();
	}

	public void delete(Caixa caixa) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(caixa);
		transaction.commit();
	}

	public List<Caixa> findAll() {
		return (List<Caixa>) manager.createQuery("select obj from Caixa obj order by obj.id  ").getResultList();
	}

	/* Filtro Busca por Codigo */
	public Caixa findByCode(Caixa caixa) {
		return manager.find(Caixa.class, caixa.getId());
	}

	public List<Caixa> findByConta(String conta) {
		
		return manager.createQuery("from Caixa where conta like :conta_pesquisada", Caixa.class)
				.setParameter("conta_pesquisada", "%" + conta + "%")
				.getResultList();
	}
}