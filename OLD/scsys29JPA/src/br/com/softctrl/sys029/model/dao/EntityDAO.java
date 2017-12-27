/**
 * 
 */
package br.com.softctrl.sys029.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 8:09:00 PM
 * 
 */
public class EntityDAO<E extends Object> implements IEntityDAO<E> {

	protected Class<?> entityClass;

	protected static final EntityManager entityManager = br.com.softctrl.sys029.model.base.connection.SCDBaseConnection
			.getInstance().getEntityManager();

	/**
	 * 
	 */
	public EntityDAO(Class<E> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	@Override
	public void insert(E entity) {
		this.validateTransaction();
		entityManager.persist(entity);
	}

	@Override
	public void update(E entity) {
		entityManager.merge(entity);
	}

	@Override
	public void remove(E entity) {
		this.validateTransaction();
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K> E findById(K id) {
		return (E) entityManager.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		// CriteriaQuery<E> qry = (CriteriaQuery<E>) entityManager
		// .getCriteriaBuilder().createQuery();
		// qry.from(entityClass);

		return entityManager.createQuery(
				String.format("from %s e", entityClass.getSimpleName()))
				.getResultList();
	}

	@Override
	public Query createQuery(String query) {
		return entityManager.createQuery(query);
	}

	public void close() {
		entityManager.close();
	}

	public void detach(E entity) {
		entityManager.detach(entity);
	}

	public void refresh(E entity) {
		entityManager.refresh(entity);
	}

	public boolean isTransactionActive() {
		return entityManager.getTransaction().isActive();
	}

	@Override
	public void begin() {
		if (!this.isTransactionActive()) {
			entityManager.getTransaction().begin();
		}
	}

	@Override
	public void commit() {
		if (this.isTransactionActive()) {
			entityManager.getTransaction().commit();
		}
	}

	@Override
	public void rollback() {
		if (this.isTransactionActive()) {
			entityManager.getTransaction().rollback();
		}
	}

	private void validateTransaction() {
		if (!this.isTransactionActive()) {
			this.begin();
		}
	}

}
