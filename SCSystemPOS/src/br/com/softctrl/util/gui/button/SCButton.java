/**
 * 
 */
package br.com.softctrl.util.gui.button;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 3:11:55 PM
 * 
 */
public class SCButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2817191206780061356L;

	// public static Border border = new LineBorder(Color.BLACK, 1);
	// static Insets margin = new Insets(1, 1, 1, 1);
	//
	// static SCButtonPlasticUI ui = new SCButtonPlasticUI();
	//
	// static {
	// UIManager.put(SCButtonPlasticUI.class.getSimpleName(),
	// SCButtonPlasticUI.class.getName());
	// }

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SCButton(String caption, Icon icon, ActionListener action) {
		super(caption, icon);
		this.addActionListener(action);
		this.setPreferredSize(new Dimension(100, 100));

	}

	// public SCButton() {
	// this(null);
	// }
	//
	// public SCButton(String text) {
	// super(text);
	// setFont(SCConfigUI.getButtonFont());
	//
	// setFocusPainted(false);
	// setMargin(margin);
	// }
	//
	// @Override
	// public String getUIClassID() {
	// return SCButtonPlasticUI.class.getSimpleName();
	// }

}
