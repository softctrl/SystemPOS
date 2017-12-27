/**
 * 
 */
package br.com.softctrl.util.gui.frame;

import br.com.softctrl.util.gui.i.ISCGUIBase;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 9:07:37 AM
 * 
 */
public abstract class SCGUIFrameApp extends javax.swing.JFrame implements
		ISCGUIBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1301788646564676165L;

	private final br.com.softctrl.sys.SCApplication app = br.com.softctrl.sys.SCApplication
			.getInstance();

	protected br.com.softctrl.sys.SCApplication getApp() {
		return app;
	}

}
