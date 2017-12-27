package br.com.softctrl.pos.gui.lov;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import br.com.softctrl.util.gui.dialog.SCGUIDialogApp;

public class SCGUIListOfValue extends SCGUIDialogApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3656919242348064016L;
	private final JPanel contentPanel = new JPanel();
	private JTable jTblLisOvValues;

	private Object value = null;
	private List<?> list = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SCGUIListOfValue dialog = new SCGUIListOfValue();
			// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SCGUIListOfValue newInstance(List<?> entities, Dialog owner,
			TableModel model) {

		SCGUIListOfValue gui = new SCGUIListOfValue(owner);
		gui.list = entities;
		gui.jTblLisOvValues.setModel(model);
		gui.jTblLisOvValues.revalidate();
		gui.jTblLisOvValues.repaint();
		return gui;

	}

	public void _show() {
		this.showMe(false);
	}

	public Object getValue() {
		return value;
	}

	public SCGUIListOfValue(Dialog owner) {
		super(owner, Dialog.ModalityType.DOCUMENT_MODAL);
		this.initialize();

	}

	/**
	 * Create the dialog.
	 */
	private SCGUIListOfValue() {
		super();
		this.initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				jTblLisOvValues = new JTable();
				scrollPane.setViewportView(jTblLisOvValues);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						int idx = jTblLisOvValues.getSelectedRow();
						value = list.get(idx);
						close();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						value = null;
						close();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void showMe(boolean modal) {
		try {
			// this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			// this.setSize(getDefaultToolKit().getScreenSize());
			this.setResizable(false);
			this.setAlwaysOnTop(modal);
			this.repaint();
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
