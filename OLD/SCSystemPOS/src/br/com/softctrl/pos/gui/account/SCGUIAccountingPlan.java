package br.com.softctrl.pos.gui.account;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import br.com.softctrl.sys029.model.AccountingPlan;
import br.com.softctrl.sys029.model.dao.EntityDAO;
import javax.swing.JTextField;

public class SCGUIAccountingPlan extends
		br.com.softctrl.util.gui.dialog.SCGUIDialogApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2577282651938333987L;
	private final JPanel contentPanel = new JPanel();

	private JTree jTreeAccountingPlan = null;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			SCGUIAccountingPlan dialog = new SCGUIAccountingPlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.jTreeAccountingPlan
					.setModel(new SCAccountingPlanTreeModel(
							(new EntityDAO<AccountingPlan>(AccountingPlan.class))
									.createQuery(
											"from AccountingPlan a where a.isAccountRoot=:ROOT")
									.setParameter("ROOT", Boolean.TRUE)
									.getResultList()));
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public SCGUIAccountingPlan() {
		setBounds(100, 100, 672, 499);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel jPnlTop = new JPanel();
			contentPanel.add(jPnlTop, BorderLayout.NORTH);
			JPanel panel_1 = new JPanel();
			GroupLayout gl_jPnlTop = new GroupLayout(jPnlTop);
			gl_jPnlTop.setHorizontalGroup(gl_jPnlTop.createParallelGroup(
					Alignment.LEADING).addGroup(
					gl_jPnlTop
							.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE,
									428, Short.MAX_VALUE).addContainerGap()));
			gl_jPnlTop.setVerticalGroup(gl_jPnlTop.createParallelGroup(
					Alignment.LEADING).addGroup(
					Alignment.TRAILING,
					gl_jPnlTop
							.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE,
									121, GroupLayout.PREFERRED_SIZE)
							.addContainerGap()));
			panel_1.setLayout(null);

			textField = new JTextField();

			textField.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent arg0) {
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
				}

				@Override
				public void keyPressed(KeyEvent arg0) {
				}
			});

			textField.setBounds(6, 6, 134, 28);
			panel_1.add(textField);
			textField.setColumns(10);
			jPnlTop.setLayout(gl_jPnlTop);
		}
		{
			JPanel jPnlAllCli = new JPanel();
			contentPanel.add(jPnlAllCli, BorderLayout.CENTER);
			jPnlAllCli.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				jPnlAllCli.add(scrollPane, BorderLayout.CENTER);
				{
					jTreeAccountingPlan = new JTree();
					scrollPane.setViewportView(jTreeAccountingPlan);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void showMe(boolean modal) {
	}
}
