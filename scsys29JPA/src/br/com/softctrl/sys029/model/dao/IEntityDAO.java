/**
 * 
 */
package br.com.softctrl.sys029.model.dao;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 8:05:31 PM
 * 
 */
public interface IEntityDAO<E extends Object> {

	void insert(E entity);

	void update(E entity);

	void remove(E entity);

	<K> E findById(K id);

	java.util.List<E> findAll();

	javax.persistence.Query createQuery(String query);

	void close();

	void detach(E entity);

	void refresh(E Entity);

	boolean isTransactionActive();

	void begin();

	void commit();

	void rollback();

}
