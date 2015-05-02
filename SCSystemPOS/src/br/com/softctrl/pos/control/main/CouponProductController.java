package br.com.softctrl.pos.control.main;

import br.com.softctrl.sys029.model.CouponProduct;
import br.com.softctrl.sys029.model.dao.EntityDAO;

public class CouponProductController {

	private static EntityDAO<CouponProduct> couponProductDAO = new EntityDAO<CouponProduct>(
			CouponProduct.class);

	public CouponProductController() {
		super();
	}

	public void save(CouponProduct couponProduct) {
		if (couponProduct != null) {
			if (couponProduct.getId() == null) {
				couponProductDAO.insert(couponProduct);
			} else {
				couponProductDAO.update(couponProduct);
			}
		}
	}

	public void remove(CouponProduct couponProduct) {

		if (couponProduct != null) {
			if (couponProduct.getId() != null) {
				couponProductDAO.remove(couponProduct);
			}
		}

	}

}
