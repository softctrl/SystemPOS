package br.com.softctrl.pos.gui.model;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 19, 2012 10:45:00 AM
 * 
 */
public class SCGUIModelCRUD extends br.com.softctrl.util.gui.dialog.SCGUIDialogApp {

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
			SCGUIModelCRUD dialog = new SCGUIModelCRUD();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SCGUIModelCRUD() {
		setBounds(100, 100, 735, 475);
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
					gl_panel_1.setHorizontalGroup(gl_panel_1
							.createParallelGroup(Alignment.LEADING).addGroup(
									gl_panel_1
											.createSequentialGroup()
											.addContainerGap()
											.addComponent(panel_2,
													GroupLayout.DEFAULT_SIZE,
													407, Short.MAX_VALUE)
											.addContainerGap()));
					gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
							Alignment.LEADING).addGroup(
							Alignment.TRAILING,
							gl_panel_1
									.createSequentialGroup()
									.addContainerGap(GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE)
									.addComponent(panel_2,
											GroupLayout.PREFERRED_SIZE, 40,
											GroupLayout.PREFERRED_SIZE)
									.addContainerGap()));
					panel_1.setLayout(gl_panel_1);
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.SOUTH);
					{
						JPanel panel_2 = new JPanel();
						panel_1.add(panel_2);
					}
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane, BorderLayout.CENTER);
					{
						table = new JTable();
						scrollPane.setViewportView(table);
					}
				}
			}
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
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
								.addContainerGap())
					);
					gl_panel_1.setVerticalGroup(
						gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
					);
					panel_1.setLayout(gl_panel_1);
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.EAST);
					JPanel panel_2 = new JPanel();
					GroupLayout gl_panel_1 = new GroupLayout(panel_1);
					gl_panel_1.setHorizontalGroup(
						gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
					);
					gl_panel_1.setVerticalGroup(
						gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addContainerGap())
					);
					panel_1.setLayout(gl_panel_1);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane, BorderLayout.CENTER);
					{
						JPanel panel_1 = new JPanel();
						scrollPane.setViewportView(panel_1);
					}
				}
			}
		}
	}

	@Override
	public void showMe(boolean modal) {
	}
}
