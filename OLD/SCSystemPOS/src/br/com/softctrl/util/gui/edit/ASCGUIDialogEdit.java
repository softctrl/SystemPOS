package br.com.softctrl.util.gui.edit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import br.com.softctrl.sys029.model.dao.EntityDAO;
import br.com.softctrl.util.gui.dialog.SCGUIDialogApp;

public abstract class ASCGUIDialogEdit<E extends Object> extends SCGUIDialogApp
		implements ISCGUIDialogEdit<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1399690975825528366L;

	protected EntityDAO<E> entityDAO = null;
	private Class<E> targetClass = null;
	private ASCGUIPanelEdit<?> editPanel = null;

	private JPanel m_contentPane;

	private JPanel panel_1;
	private JPanel panel_2;
	private JButton jBtnPersist;
	private JButton jBtnCancel;
	private JButton jBtnRemove;
	private JPanel panel;

	public ASCGUIDialogEdit() {
		super();
		this.initializeComponents();
	}

	public ASCGUIDialogEdit(ASCGUIPanelEdit<?> editPanel, Class<E> targetClass) {
		super();
		this.editPanel = editPanel;
		this.targetClass = targetClass;
		this.initializeComponents();
		this.getContentPane().add(editPanel, BorderLayout.CENTER);
		this.entityDAO = new EntityDAO<E>(targetClass);

	}

	public ASCGUIDialogEdit(ASCGUIPanelEdit<?> editPanel, Class<E> targetClass,
			SCGUIDialogApp owner) {

		super(owner, ModalityType.APPLICATION_MODAL);
		this.editPanel = editPanel;
		this.targetClass = targetClass;
		this.initializeComponents();
		this.getContentPane().add(editPanel, BorderLayout.CENTER);
		this.entityDAO = new EntityDAO<E>(targetClass);

	}

	protected void initDataBindings() {
		this.editPanel.initDataBindings();
	}

	protected void initializeComponents() {
		setBounds(100, 100, 483, 191);
		m_contentPane = new JPanel();
		m_contentPane.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(
				0, 0, 0)));
		setContentPane(m_contentPane);
		m_contentPane.setLayout(null);
		m_contentPane.setLayout(new BorderLayout(0, 0));
		panel_1 = new JPanel();
		m_contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 0, 1, 1,
				(Color) new Color(0, 0, 0)));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 444,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 46,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		panel_2.setLayout(new GridLayout(0, 4, 0, 0));
		jBtnCancel = new JButton("Cancelar");
		jBtnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Cancelar.........");
			}
		});
		panel_2.add(jBtnCancel);
		jBtnPersist = new JButton("Salvar");
		jBtnPersist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (forInsert() && beforeInsert()) {
					entityDAO.begin();
					entityDAO.insert(getEntity());
					entityDAO.commit();
					afterInsert();

				} else if (beforeUpdate()) {
					entityDAO.begin();
					entityDAO.update(getEntity());
					entityDAO.commit();
					afterUpdate();
				}

			}
		});

		panel = new JPanel();
		panel_2.add(panel);

		jBtnRemove = new JButton("Excluir");
		jBtnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (canRemove() && beforeRemove()) {
					entityDAO.begin();
					entityDAO.remove(getEntity());
					entityDAO.commit();
					afterRemove();
				}

			}
		});
		panel_2.add(jBtnRemove);
		panel_2.add(jBtnPersist);
		panel_1.setLayout(gl_panel_1);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void setEntity(E entity) {
		this.editPanel.setEntity(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getEntity() {
		return (E) this.editPanel.getEntity();
	}

	public abstract boolean forInsert();

	public abstract boolean canRemove();

	public void afterInsert() {
	}

	public boolean beforeInsert() {
		return true;
	}

	public void afterUpdate() {
	}

	public boolean beforeUpdate() {
		return true;
	}

	public void afterRemove() {
	}

	public boolean beforeRemove() {
		return true;
	}

}
