///**
// * 
// */
//package br.com.softctrl.pos.control.sales;
//
//import javax.persistence.Query;
//
//import br.com.softctrl.pos.control.base.SCBaseController;
//import br.com.softctrl.pos.control.main.CouponController;
//import br.com.softctrl.sys029.model.Coupon;
//import br.com.softctrl.sys029.model.Product;
//
///**
// * @author carlostimoshenkorodrigueslopes@gmail.com
// * @date Sep 16, 2012 11:44:10 AM
// * 
// */
//public class SalesController extends SCBaseController<Coupon> {
//
//	/**
//	 * 
//	 */
//	public SalesController() {
//		super();
//	}
//
//	@SuppressWarnings("unchecked")
//	public java.util.List<Product> getProducts() {
//		Query qry = getEntityManager().createQuery("from Product p");
//		return qry.getResultList();
//	}
//	
//	
//
// }
