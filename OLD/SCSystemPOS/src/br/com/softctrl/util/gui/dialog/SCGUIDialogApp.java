/**
 * 
 */
package br.com.softctrl.util.gui.dialog;

import java.awt.Dialog;
import java.awt.Toolkit;

import br.com.softctrl.pos.util.Messages;
import br.com.softctrl.util.gui.i.ISCGUIBase;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 9:07:37 AM
 * 
 */
public abstract class SCGUIDialogApp extends javax.swing.JDialog implements
		ISCGUIBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1301788646564676165L;

	public SCGUIDialogApp() {
		super();
	}

	public SCGUIDialogApp(Dialog owner, Dialog.ModalityType modalityType) {
		super(owner, modalityType);
	}

	public SCGUIDialogApp(Dialog owner) {
		super(owner);
	}

	private final br.com.softctrl.sys.SCApplication app = br.com.softctrl.sys.SCApplication
			.getInstance();

	protected br.com.softctrl.sys.SCApplication getApp() {
		return app;
	}

	public Toolkit getDefaultToolKit() {
		return Toolkit.getDefaultToolkit();
	}

	@Override
	public String getString(String key) {
		return Messages.getString(key);
	}

	public void close() {
		this.dispose();
	}

}
