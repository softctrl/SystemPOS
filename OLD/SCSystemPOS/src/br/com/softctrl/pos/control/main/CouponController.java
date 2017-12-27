/**
 * 
 */
package br.com.softctrl.pos.control.main;

import java.util.Date;
import java.util.List;

import br.com.softctrl.sys029.model.Coupon;
import br.com.softctrl.sys029.model.CouponProduct;
import br.com.softctrl.sys029.model.Restaurant;
import br.com.softctrl.sys029.model.RestaurantTable;
import br.com.softctrl.sys029.model.dao.EntityDAO;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 4:22:05 PM
 * 
 */
public final class CouponController {

	private static EntityDAO<RestaurantTable> restaurantTableDAO = new EntityDAO<RestaurantTable>(
			RestaurantTable.class);

	private static EntityDAO<Coupon> couponDAO = new EntityDAO<Coupon>(
			Coupon.class);
	private static EntityDAO<CouponProduct> couponProductDAO = new EntityDAO<CouponProduct>(
			CouponProduct.class);

	// private static EntityDAO<Product> productDAO = new EntityDAO<Product>(
	// Product.class);

	public CouponController() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Coupon> findAllCouponsInTableInDay(Integer tableNumber,
			Date date) {

		return couponDAO
				.createQuery(
						"from Coupon c where c.tableNumber=:TABLE_NUMBER and c.creationDate>=:DATE")
				.setParameter("TABLE_NUMBER", tableNumber)
				.setParameter("DATE", date).getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findAllTablesInRestaurantCode(Restaurant restaurant) {

		return restaurantTableDAO
				.createQuery(
						"select rt.id, rt.code, rt.description from RestaurantTable rt where rt.restaurant=:RESTAURANT")
				.setParameter("RESTAURANT", restaurant).getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Coupon> findAllCouponsInDay(Date date) {

		return couponDAO
				.createQuery("from Coupon c where c.creationDate>=:DATE")
				.setParameter("DATE", date).getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Coupon> findAllCouponsByRestaurantTableOnDate(
			RestaurantTable restaurantTable, Date date) {

		return couponDAO
				.createQuery(
						"from Coupon c where c.restaurantTable=:TABLE and c.creationDate>=:DATE")
				.setParameter("TABLE", restaurantTable)
				.setParameter("DATE", date).getResultList();

	}

	public Coupon findById(Integer id) {

		return couponDAO.findById(id);
	}

	public Coupon save(Coupon coupon) {

		if (coupon != null) {
			couponDAO.begin();
			if (coupon.getId() == null) {
				couponDAO.insert(coupon);
			} else {
				couponDAO.update(coupon);
			}
			for (CouponProduct cp : coupon.getCouponProducts()) {
				if (cp.getId() == null) {
					couponProductDAO.insert(cp);
				} else {
					couponProductDAO.update(cp);
				}
			}
			couponDAO.commit();
		}
		return coupon;
	}

	public void delete(Coupon coupon) {

		if (coupon != null && coupon.getId() != null) {
			couponDAO.begin();
			for (CouponProduct cp : coupon.getCouponProducts()) {
				couponProductDAO.remove(cp);
			}
			couponDAO.remove(coupon);
			couponDAO.commit();
		}

	}

	public void cancel() {
		couponDAO.rollback();
	}

	public static void main(String[] args) {
		CouponController c = new CouponController();
		EntityDAO<Restaurant> dao = new EntityDAO<Restaurant>(Restaurant.class);
		// System.out.println(c.findAllTablesInRestaurantCode(dao.findById(1)));
		for (Object[] obj : c.findAllTablesInRestaurantCode(dao.findById(1))) {
			for (Object o : obj) {
				System.out.println(o);
			}
		}
	}
}
