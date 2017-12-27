package br.com.softctrl.util.gui.find;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.com.softctrl.sys029.model.AccountingPlan;
import br.com.softctrl.sys029.model.dao.EntityDAO;
import br.com.softctrl.util.gui.dialog.SCGUIDialogApp;

public class SCGUIEntityFind extends SCGUIDialogApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7300940226954745258L;

	private final JPanel contentPanel = new JPanel();
	private JTable jTblEntity;

	private JButton jBtnClose = null;
	private JButton jBtnNewEntity = null;
	private JButton jBtnEditEntity = null;
	private JButton jBtnRemoveEntity = null;

	private List<AccountingPlan> entities = null;

	private static final MatteBorder MATTER_BORDER_2012 = new MatteBorder(2, 0,
			1, 2, (Color) new Color(0, 0, 0));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SCGUIEntityFind dialog = new SCGUIEntityFind();

			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			EntityDAO<AccountingPlan> dao = new EntityDAO<AccountingPlan>(
					AccountingPlan.class);
			dialog.jTblEntity.setModel(new SCEntityTableTableModel(dao
					.findAll()));
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SCGUIEntityFind() {
		setBounds(100, 100, 674, 473);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		contentPanel.add(panel_2, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_2
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 652,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panel_2
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 43,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		panel_2.setLayout(gl_panel_2);

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		jTblEntity = new JTable();
		jTblEntity.setBorder(MATTER_BORDER_2012);
		scrollPane.setViewportView(jTblEntity);

		JPanel panel_4 = new JPanel();
		contentPanel.add(panel_4, BorderLayout.SOUTH);

		JPanel panel_5 = new JPanel();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_4
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 652,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panel_4
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 25,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_1 = new JButton("<<");
		panel_5.add(btnNewButton_1);

		JButton btnNewButton = new JButton("<");
		panel_5.add(btnNewButton);

		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);

		JButton button_1 = new JButton(">");
		panel_5.add(button_1);

		JButton button = new JButton(">");
		panel_5.add(button);
		panel_4.setLayout(gl_panel_4);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			JPanel panel = new JPanel();
			panel.setBorder(MATTER_BORDER_2012);
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(
					Alignment.LEADING).addGroup(
					gl_buttonPane
							.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 438,
									Short.MAX_VALUE).addContainerGap()));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(
					Alignment.LEADING).addGroup(
					Alignment.TRAILING,
					gl_buttonPane
							.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE,
									52, GroupLayout.PREFERRED_SIZE)
							.addContainerGap()));
			panel.setLayout(new GridLayout(1, 0, 0, 0));

			jBtnClose = new JButton("Fechar");
			jBtnClose.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Fechar..");
				}
			});
			panel.add(jBtnClose);

			JPanel panel_1 = new JPanel();
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));

			JProgressBar jPgbProccess = new JProgressBar();
			panel_1.add(jPgbProccess, BorderLayout.CENTER);

			jBtnNewEntity = new JButton("Novo");
			jBtnNewEntity.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Novo..");
				}
			});
			panel.add(jBtnNewEntity);

			jBtnEditEntity = new JButton("Editar");
			jBtnEditEntity.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Editar..");
				}
			});
			panel.add(jBtnEditEntity);

			jBtnRemoveEntity = new JButton("Excluir");
			jBtnRemoveEntity.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Excluir..");
				}
			});
			panel.add(jBtnRemoveEntity);
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	public AccountingPlan getSelectedEntity() {
		return null;
	}

	@Override
	public void showMe(boolean modal) {
	}
}
