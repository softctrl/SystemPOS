/**
 * 
 */
package br.com.softctrl.sys029.model.base.connection;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 13, 2012 9:44:18 PM
 * 
 */
public final class SCDBaseConnection {

	private javax.persistence.EntityManagerFactory factory = null;
	private javax.persistence.EntityManager manager = null;

	private static SCDBaseConnection THIS;

	private SCDBaseConnection() {
		super();
		factory = javax.persistence.Persistence
				.createEntityManagerFactory("scsys29JPA");
		manager = factory.createEntityManager();
	}

	public static SCDBaseConnection getInstance() {

		if (THIS == null) {
			THIS = new SCDBaseConnection();
		}

		return THIS;

	}

	public javax.persistence.EntityManager getEntityManager() {
		return this.manager;
	}

	public void close() {
		if (this.factory != null) {
			this.manager.close();
			this.factory.close();

			this.factory = null;
			this.manager = null;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (this.manager != null && this.manager.isOpen()) {
			this.close();
		}
		super.finalize();
	}

}
