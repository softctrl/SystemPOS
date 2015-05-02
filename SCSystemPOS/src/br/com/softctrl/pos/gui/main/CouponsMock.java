/**
 * 
 */
package br.com.softctrl.pos.gui.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.softctrl.sys029.model.Coupon;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 10:15:17 PM
 * 
 */
public final class CouponsMock {

	public static List<Coupon> getCoupons() {
		List<Coupon> coupons = new ArrayList<Coupon>();
		for (int i = 0; i < 1000; i++) {
			Coupon c = new Coupon();
			c.setCode("" + i);
			c.setCreationDate(new Timestamp(new Date().getTime()));
			// c.setTableNumber(i);
			coupons.add(c);
		}
		return coupons;
	}

}
