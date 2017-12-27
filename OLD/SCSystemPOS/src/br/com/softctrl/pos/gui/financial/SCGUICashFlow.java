package br.com.softctrl.pos.gui.financial;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SCGUICashFlow extends br.com.softctrl.util.gui.dialog.SCGUIDialogApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1834189553998516913L;

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SCGUICashFlow dialog = new SCGUICashFlow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SCGUICashFlow() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("New tab", null, panel, null);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.NORTH);
					
					JPanel panel_2 = new JPanel();
					GroupLayout gl_panel_1 = new GroupLayout(panel_1);
					gl_panel_1.setHorizontalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
								.addContainerGap())
					);
					gl_panel_1.setVerticalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
					);
					panel_1.setLayout(gl_panel_1);
				}
				
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				
				table = new JTable();
				scrollPane.setViewportView(table);
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("New tab", null, panel, null);
				panel.setLayout(new BorderLayout(0, 0));
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
