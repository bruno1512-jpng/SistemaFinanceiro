package br.com.sxsolucoes.financeiro.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.sxsolucoes.financeiro.entity.FluxoCaixa;



public class FluxoCaixaDAO {

	EntityManager manager;
	EntityTransaction transaction;
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("financeiro");

	public FluxoCaixaDAO() {
		manager = factory.createEntityManager();
	}

	public void gravar(FluxoCaixa fluxo) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(fluxo);
		manager.flush();
		transaction.commit();
	}

	public void alterar(FluxoCaixa fluxo) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(fluxo);
		transaction.commit();
	}

	public void apagar(FluxoCaixa fluxo) throws Exception {
		transaction = manager.getTransaction();
		transaction.begin();
		manager.remove(fluxo);
		transaction.commit();
	}

	public List<FluxoCaixa> listarTodos() {
		return (List<FluxoCaixa>) manager.createQuery("select obj from FluxoCaixa obj order by obj.id").getResultList();
	}

	/* Filtro Busca por Codigo|Id */
	public FluxoCaixa buscaPorCodigo(FluxoCaixa fluxo) {
		return manager.find(FluxoCaixa.class, fluxo.getId());
	}

}