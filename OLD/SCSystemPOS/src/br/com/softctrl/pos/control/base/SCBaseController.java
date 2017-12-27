///**
// * 
// */
//package br.com.softctrl.pos.control.base;
//
//import javax.persistence.FlushModeType;
//
///**
// * @author carlostimoshenkorodrigueslopes@gmail.com
// * @date Sep 14, 2012 9:52:34 AM
// * 
// */
//public class SCBaseController<T extends Object> {
//
//	private javax.persistence.EntityManager em = br.com.softctrl.sys029.model.base.connection.SCDBaseConnection
//			.getInstance().getEntityManager();
//
//	/**
//	 * 
//	 */
//	public SCBaseController() {
//		super();
//		this.getEntityManager().setFlushMode(FlushModeType.COMMIT);
//	}
//
//	protected javax.persistence.EntityManager getEntityManager() {
//		return em;
//	}
//
// }
