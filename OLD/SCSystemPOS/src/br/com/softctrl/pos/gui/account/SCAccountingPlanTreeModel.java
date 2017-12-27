/**
 * 
 */
package br.com.softctrl.pos.gui.account;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.com.softctrl.sys029.model.AccountingPlan;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 18, 2012 10:43:37 PM
 * 
 */
public class SCAccountingPlanTreeModel extends DefaultTreeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1279257903459144672L;

	public SCAccountingPlanTreeModel(
			final java.util.List<AccountingPlan> accountingPlans) {

		super(new DefaultMutableTreeNode("Accounts") {

			private static final long serialVersionUID = 1116226578498831664L;

			{
				for (AccountingPlan acp : accountingPlans) {
					addNode(this, acp);
				}
			}
		});
	}

	private static void addNode(DefaultMutableTreeNode father,
			AccountingPlan accountingPlan) {

		DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(String.format(
				"%s-%s", accountingPlan.getCode(),
				accountingPlan.getDescription()));
		father.add(dmtn);
		for (AccountingPlan acp : accountingPlan.getAccountingChildren()) {
			addNode(dmtn, acp);
		}

	}
}
