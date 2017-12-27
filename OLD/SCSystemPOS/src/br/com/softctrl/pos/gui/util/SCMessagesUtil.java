package br.com.softctrl.pos.gui.util;

import java.awt.Component;
import java.awt.Dialog;

import javax.swing.JOptionPane;

/**
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 17, 2012 10:13:47 AM
 * 
 */
public class SCMessagesUtil extends br.com.softctrl.util.gui.dialog.SCGUIDialogApp {
	public SCMessagesUtil() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3529159052016733175L;
	private static Dialog dialog = null;

	public static void showMessage(String message) {

		JOptionPane.showMessageDialog(dialog, message,
				"com.floreantpos.POSConstants.MDS_POS",
				JOptionPane.INFORMATION_MESSAGE, null);
	}

	public static void showError(String message) {
		JOptionPane.showMessageDialog(dialog, message,
				"com.floreantpos.POSConstants.MDS_POS",
				JOptionPane.ERROR_MESSAGE, null);
	}

	public static void showError(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message,
				"com.floreantpos.POSConstants.MDS_POS",
				JOptionPane.ERROR_MESSAGE, null);
	}

	public static void showError(String message, Throwable x) {
		x.printStackTrace();
		JOptionPane.showMessageDialog(dialog, message,
				"com.floreantpos.POSConstants.ERROR_MESSAGE",
				JOptionPane.ERROR_MESSAGE, null);
	}

	public static void showError(Component parent, String message, Throwable x) {
		x.printStackTrace();
		JOptionPane.showMessageDialog(parent, message,
				"com.floreantpos.POSConstants.MDS_POS",
				JOptionPane.ERROR_MESSAGE, null);
	}

	public static int showConfirmation(Component parent, String message) {
		return JOptionPane.showConfirmDialog(parent, message);
	}

	@Override
	@Deprecated
	public void showMe(boolean modal) {
	}

	public static void main(String[] args) {
		System.out.println(showConfirmation(null, "Deseja fazer algo?"));
	}
}
