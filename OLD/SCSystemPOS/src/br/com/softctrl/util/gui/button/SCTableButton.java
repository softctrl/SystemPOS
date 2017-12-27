/**
 * 
 */
package br.com.softctrl.util.gui.button;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;

import br.com.softctrl.sys029.model.Coupon;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 4:03:12 PM
 * 
 */
public class SCTableButton extends SCButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 328070177659081029L;

	private Coupon coupon = null;
	private int id = -1;
	private int tableCode = -1;
	private Integer guestNumber;

	/**
	 * @param caption
	 * @param icon
	 * @param tableNumber
	 * @param action
	 */
	public SCTableButton(Icon icon, int id, int tableCode, String description,
			Integer guestNumber, ActionListener action, Coupon coupon) {

		super(description, icon, action);
		this.setPreferredSize(new Dimension(160, 80));
		this.setFocusable(false);
		this.setId(id);
		this.setTableCode(tableCode);
		this.setGuestNumber(guestNumber);
		this.setCoupon(coupon);

	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Integer getGuestNumber() {
		return guestNumber;
	}

	public void setGuestNumber(Integer guestNumber) {
		this.guestNumber = guestNumber;
	}

	public int getTableCode() {
		return tableCode;
	}

	private void setTableCode(int tableCode) {
		this.tableCode = tableCode;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

}
