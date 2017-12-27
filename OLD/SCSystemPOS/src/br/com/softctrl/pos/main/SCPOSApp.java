/**
 * 
 */
package br.com.softctrl.pos.main;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 13, 2012 9:52:56 PM
 * 
 */
public class SCPOSApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					br.com.softctrl.pos.gui.login.SCGUILogin form = new br.com.softctrl.pos.gui.login.SCGUILogin();
					form.showMe(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
