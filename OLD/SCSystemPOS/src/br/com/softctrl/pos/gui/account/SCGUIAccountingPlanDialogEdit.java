/**
 * 
 */
package br.com.softctrl.pos.gui.account;

import br.com.softctrl.sys029.model.AccountingPlan;
import br.com.softctrl.util.gui.edit.ASCGUIDialogEdit;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 19, 2012 4:06:50 PM
 * 
 */
public class SCGUIAccountingPlanDialogEdit extends
		ASCGUIDialogEdit<AccountingPlan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4755625902279032849L;

	public static void main(String[] args) {

		SCGUIAccountingPlanPanelEdit editPanel = new SCGUIAccountingPlanPanelEdit();

		SCGUIAccountingPlanDialogEdit d = new SCGUIAccountingPlanDialogEdit(
				editPanel);

		d.setEntity(d.entityDAO.findById(24));
		d.initDataBindings();
		d.showMe(false);

	}

	/**
	 * @param editPanel
	 * @param entity
	 */
	protected SCGUIAccountingPlanDialogEdit(
			SCGUIAccountingPlanPanelEdit editPanel) {
		super(editPanel, AccountingPlan.class);
		this.setResizable(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.util.gui.i.ISCGUIBase#showMe(boolean)
	 */
	@Override
	public void showMe(boolean modal) {
		setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.softctrl.pos.gui.account.ASCGUIDialogEdit#forInsert()
	 */
	@Override
	public boolean forInsert() {
		return this.getEntity().getId() == null;
	}

	@Override
	public boolean canRemove() {
		return this.getEntity().getId() != null;
	}

}
