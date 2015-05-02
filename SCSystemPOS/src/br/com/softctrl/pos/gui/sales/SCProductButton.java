/**
 * 
 */
package br.com.softctrl.pos.gui.sales;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;

import com.jgoodies.looks.plastic.PlasticButtonUI;

import br.com.softctrl.sys029.model.Product;
import br.com.softctrl.util.gui.button.SCButton;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 11:50:46 AM
 * 
 */
public class SCProductButton extends SCButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1653969931799119064L;

	private Product product = null;

	/**
	 * @param caption
	 * @param icon
	 * @param action
	 */
	public SCProductButton(final Product product, String caption, Icon icon,
			ActionListener action) {
		super(caption, icon, action);
		PlasticButtonUI.createUI(this);
		this.setPreferredSize(new Dimension(200, 100));
		this.setFocusable(false);
		this.setProduct(product);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
