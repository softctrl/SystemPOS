package br.com.softctrl.pos.gui.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.softctrl.pos.control.main.CouponController;
import br.com.softctrl.pos.gui.lov.SCGUIListOfValue;
import br.com.softctrl.pos.gui.restaurant.SCGUIRestaurantDialogEdit;
import br.com.softctrl.pos.gui.sales.SCCouponsProductTableModel;
import br.com.softctrl.pos.gui.sales.SCGUISales;
import br.com.softctrl.pos.gui.sales.SCRestaurantTableTableModel;
import br.com.softctrl.pos.gui.util.SCMessagesUtil;
import br.com.softctrl.sys029.model.Coupon;
import br.com.softctrl.sys029.model.CouponProduct;
import br.com.softctrl.sys029.model.RestaurantTable;
import br.com.softctrl.sys029.model.dao.EntityDAO;
import br.com.softctrl.util.gui.button.SCTableButton;
import br.com.softctrl.util.gui.dialog.SCGUIDialogApp;
import br.com.softctrl.util.gui.icon.SCIconFactory;
import br.com.softctrl.util.gui.layout.WrapLayout;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;
import org.jdesktop.swingx.JXHyperlink;

public class SCGUIMainForm extends SCGUIDialogApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4186450630070623853L;

	// private static final SimpleDateFormat sdf = new SimpleDateFormat(
	// "dd/MM/yyyy");

	private List<Coupon> coupons = null;
	private RestaurantTable seletedTable = null;

	private SCGUISales formSales = null;

	private final CouponController couponController = new CouponController();

	private JMenuBar menuBar = null;

	private JTabbedPane jTbpCoupons = null;
	private JTable jTblOpenCoupons = null;
	private JTable jTblItemsSelectedCoupon = null;

	private JButton jBtnNewCoupon = null;
	private JButton jBtnEditCoupon = null;
	private JButton jBtnCancelCoupon = null;

	private JXMonthView jXMonthView = null;

	private JButton jBtnRefreshTables = null;

	private JPanel jPnlPageViewTables = null;

	private SCGUIMainForm _THIS;

	private void startNewCoupon(RestaurantTable table, Integer guestCount,
			Integer fileNumber) {

		try {
			if (guestCount == null)
				guestCount = 5;

			this.formSales = SCGUISales.newInstance(_THIS, new Coupon(table,
					guestCount, fileNumber, getApp().getUser()));

			this.formSales.showMe(false);
			refreshOpenCoupons();
		} catch (Exception e) {
			SCMessagesUtil.showError(_THIS,
					"Erro na aplicação\n" + e.getMessage());

		}

	}

	private void startEditCoupon() {

		int selectIndex = this.jTblOpenCoupons.getSelectedRow();
		if (selectIndex != -1) {
			this.formSales = SCGUISales.newInstance(_THIS,
					this.coupons.get(selectIndex));
			this.formSales.showMe(false);
			// this.coupons.get(selectIndex)
			refreshOpenCoupons();
		}

	}

	private final ActionListener refreshTableListActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			_THIS.refreshRestaurantTables();
		}
	};

	private final ActionListener cancelCouponActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			//
			int selectIndex = SCGUIMainForm.this.jTblOpenCoupons
					.getSelectedRow();
			if (selectIndex != -1) {
				Coupon c = SCGUIMainForm.this.coupons.get(selectIndex);
				if (SCMessagesUtil
						.showConfirmation(
								_THIS,
								getString(c.toString()
										+ "\nDeseja realmente excluir o cupom selecionado?")) == 0) {
					couponController.delete(c);
					refreshOpenCoupons();
				}
			} else {
				SCMessagesUtil
						.showMessage("É necessário selecionar um cupom para em seguida excluir/cancelar o mesmo.");
			}

		}
	};

	private final ActionListener newCouponActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (_THIS.seletedTable == null) {

				EntityDAO<RestaurantTable> tableDAO = new EntityDAO<RestaurantTable>(
						RestaurantTable.class);
				final List<RestaurantTable> tables = tableDAO.findAll();
				SCGUIListOfValue lov = SCGUIListOfValue.newInstance(tables,
						_THIS, new SCRestaurantTableTableModel(tables));
				lov._show();
				if (lov.getValue() != null) {
					_THIS.seletedTable = (RestaurantTable) lov.getValue();
				}
				System.out.println(lov.getValue());
				// TODO desenvolver apresentar tela de selecionar mesa
			}
			startNewCoupon(_THIS.seletedTable, 3, null);
		}
	};

	private final ActionListener editCouponActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			startEditCoupon();
		}
	};

	final ActionListener findCouponsByTableActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actevt) {

			EntityDAO<RestaurantTable> tableDAO = new EntityDAO<RestaurantTable>(
					RestaurantTable.class);
			_THIS.seletedTable = tableDAO.findById(((SCTableButton) actevt
					.getSource()).getTableCode());

			_THIS.coupons = _THIS.couponController
					.findAllCouponsByRestaurantTableOnDate(_THIS.seletedTable,
							getApp().getLoggedIn());
			if (_THIS.coupons != null && _THIS.coupons.size() > 0) {
				_THIS.jTblOpenCoupons.setModel(new SCCouponsTableModel(
						_THIS.coupons));
				_THIS.jTbpCoupons.setSelectedIndex(1);
				_THIS.jTblOpenCoupons.setRowSelectionInterval(0, 0);
			} else {
				switch (SCMessagesUtil.showConfirmation(_THIS,
						getString("DONT_EXISTS_COUPON_CREATE_NEW"))) {
				case 0:
					startNewCoupon(_THIS.seletedTable, null, null);
					break;
				}
			}

		}
	};

	private void refreshRestaurantTables() {

		Component[] comps = jPnlPageViewTables.getComponents();

		for (Component c : comps) {
			jPnlPageViewTables.remove(c);
		}
		comps = null;

		for (Object[] rest : couponController
				.findAllTablesInRestaurantCode(getApp().getUser()
						.getRestaurant())) {
			SCTableButton btn = new SCTableButton(SCIconFactory.TABLE_ICON_1,
					(Integer) rest[0], (Integer) rest[1], rest[2].toString(),
					5, findCouponsByTableActionListener, null);
			jPnlPageViewTables.add(btn);
			jPnlPageViewTables.validate();
			jPnlPageViewTables.repaint();

		}

	}

	private void refreshOpenCoupons() {

		// this.coupons = couponController.findAllCouponsInDay(getApp()
		// .getLoggedIn());

		this.coupons = _THIS.couponController
				.findAllCouponsByRestaurantTableOnDate(_THIS.seletedTable,
						getApp().getLoggedIn());
		jTblOpenCoupons.setModel(new SCCouponsTableModel(coupons));
		jTblOpenCoupons.getColumnModel().getColumn(0)
				.setCellRenderer(SCCouponsTableModel.CENTER_ALIGN);
		jTblOpenCoupons.getColumnModel().getColumn(1)
				.setCellRenderer(SCCouponsTableModel.CENTER_ALIGN);
		jTblOpenCoupons.getColumnModel().getColumn(2)
				.setCellRenderer(SCCouponsTableModel.CENTER_ALIGN);
		jTblOpenCoupons.getColumnModel().getColumn(3)
				.setCellRenderer(SCCouponsTableModel.RIGHT_ALIGN);

		jTblOpenCoupons.revalidate();
		jTblOpenCoupons.repaint();

	}

	/**
	 * Create the dialog.
	 */
	public SCGUIMainForm() {
		setBounds(100, 100, 800, 800);

		JPanel jPnlAllClient = new JPanel();
		jPnlAllClient.setBorder(null);
		getContentPane().add(jPnlAllClient, BorderLayout.CENTER);

		jTbpCoupons = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_jPnlAllClient = new GroupLayout(jPnlAllClient);
		gl_jPnlAllClient.setHorizontalGroup(gl_jPnlAllClient
				.createParallelGroup(Alignment.LEADING).addGroup(
						gl_jPnlAllClient
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jTbpCoupons,
										GroupLayout.DEFAULT_SIZE, 207,
										Short.MAX_VALUE).addContainerGap()));
		gl_jPnlAllClient.setVerticalGroup(gl_jPnlAllClient.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_jPnlAllClient
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jTbpCoupons, GroupLayout.DEFAULT_SIZE,
								141, Short.MAX_VALUE).addContainerGap()));

		JScrollPane jScpViewTables = new JScrollPane();
		jTbpCoupons.addTab(getString("TABLES"), null, jScpViewTables, null);

		jPnlPageViewTables = new JPanel();
		WrapLayout wl_jPnlPageViewTables = new WrapLayout();
		wl_jPnlPageViewTables.setAlignment(FlowLayout.LEFT);
		jPnlPageViewTables.setLayout(wl_jPnlPageViewTables);
		jScpViewTables.setViewportView(jPnlPageViewTables);

		JPanel jPnlCouponsDetail = new JPanel();
		jTbpCoupons.addTab(getString("COUPONS_IN_TABLE"), null,
				jPnlCouponsDetail, null);
		jPnlCouponsDetail.setLayout(new BorderLayout(0, 0));

		JPanel jPnlItemsInCoupon = new JPanel();
		jPnlCouponsDetail.add(jPnlItemsInCoupon, BorderLayout.EAST);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel_2 = new JPanel();
		GroupLayout gl_jPnlItemsInCoupon = new GroupLayout(jPnlItemsInCoupon);
		gl_jPnlItemsInCoupon
				.setHorizontalGroup(gl_jPnlItemsInCoupon
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_jPnlItemsInCoupon
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_jPnlItemsInCoupon
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_jPnlItemsInCoupon
																		.createSequentialGroup()
																		.addComponent(
																				panel_2,
																				GroupLayout.DEFAULT_SIZE,
																				333,
																				Short.MAX_VALUE)
																		.addGap(6))
														.addComponent(
																tabbedPane,
																Alignment.TRAILING,
																GroupLayout.PREFERRED_SIZE,
																339,
																GroupLayout.PREFERRED_SIZE))));
		gl_jPnlItemsInCoupon.setVerticalGroup(gl_jPnlItemsInCoupon
				.createParallelGroup(Alignment.LEADING).addGroup(
						gl_jPnlItemsInCoupon
								.createSequentialGroup()
								.addComponent(tabbedPane,
										GroupLayout.PREFERRED_SIZE, 281,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_2,
										GroupLayout.DEFAULT_SIZE, 146,
										Short.MAX_VALUE).addContainerGap()));
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Ítens do cupon Selecionado", null, scrollPane, null);

		jTblItemsSelectedCoupon = new JTable();
		scrollPane.setViewportView(jTblItemsSelectedCoupon);
		jPnlItemsInCoupon.setLayout(gl_jPnlItemsInCoupon);

		JPanel jPnl000 = new JPanel();
		jPnlCouponsDetail.add(jPnl000, BorderLayout.CENTER);
		jPnl000.setLayout(new BorderLayout(0, 0));

		JPanel jPnlActions = new JPanel();
		// jTbpActions.addTab(getString("ACTIONS"), null, jPnlActions, null);

		jPnlActions.setLayout(new GridLayout(2, 10, 0, 0));

		jBtnNewCoupon = new JButton(getString("main.NEW_COUPON"),
				SCIconFactory.getSysIcon("new_ticket_32.png"));
		jBtnNewCoupon.addActionListener(newCouponActionListener);
		jPnlActions.add(jBtnNewCoupon);

		jBtnEditCoupon = new JButton(getString("main.EDIT_COUPON"),
				SCIconFactory.getSysIcon("edit_ticket_32.png"));
		jBtnEditCoupon.addActionListener(editCouponActionListener);
		jPnlActions.add(jBtnEditCoupon);

		JButton jBtnSplitCoupon = new JButton(getString("Dividir"));
		jPnlActions.add(jBtnSplitCoupon);

		JButton jBtnGroupCoupon = new JButton(getString("Agrupar"));
		jPnlActions.add(jBtnGroupCoupon);

		jBtnCancelCoupon = new JButton(getString("main.CANCEL_COUPON"),
				SCIconFactory.getSysIcon("cancel_32.png"));
		jBtnCancelCoupon.addActionListener(cancelCouponActionListener);
		jPnlActions.add(jBtnCancelCoupon);

		JButton btnNewButton_1 = new JButton(getString("main.CHANGE_TABLE"));
		jPnlActions.add(btnNewButton_1);

		jBtnRefreshTables = new JButton(getString("Listar Mesas"));
		jBtnRefreshTables.addActionListener(refreshTableListActionListener);
		jPnlActions.add(jBtnRefreshTables);

		JButton btnNewButton_4 = new JButton(getString("New button"));
		jPnlActions.add(btnNewButton_4);

		// JPanel panel = new JPanel();
		jPnl000.add(jPnlActions, BorderLayout.SOUTH);

		// panel_3.add(arg0);

		JScrollPane jScpViewCoupons = new JScrollPane();
		jPnl000.add(jScpViewCoupons, BorderLayout.CENTER);

		jTblOpenCoupons = new JTable();

		// TODO timoshenko
		jTblOpenCoupons.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent evt) {

						if (evt.getValueIsAdjusting()) {
							return;
						}
						if (jTblOpenCoupons.getSelectedRow() > -1) {
							_THIS.setupGrid(_THIS.coupons.get(
									jTblOpenCoupons.getSelectedRow())
									.getCouponProducts());
							// TODO remover
							System.out.println("Row Selected.: "
									+ _THIS.coupons.get(jTblOpenCoupons
											.getSelectedRow()));
							System.out.println(_THIS.coupons
									.get(jTblOpenCoupons.getSelectedRow()));
						}

					}
				});

		jTblOpenCoupons.setDragEnabled(true);

		// jTblOpenCoupons.

		jTblOpenCoupons.setRowHeight(jTblOpenCoupons.getRowHeight() * 2);
		jTblOpenCoupons.setFont(new Font("Times New Roman", Font.PLAIN, 24));

		jScpViewCoupons.setViewportView(jTblOpenCoupons);

		JTabbedPane jTbpActions = new JTabbedPane(JTabbedPane.TOP);
		jScpViewCoupons.setColumnHeaderView(jTbpActions);

		JPanel panel = new JPanel();
		jTbpCoupons.addTab("Resumo Diário", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGap(0, 865, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGap(0, 80, Short.MAX_VALUE));
		panel_1.setLayout(gl_panel_1);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.WEST);

		jXMonthView = new JXMonthView();
		jXMonthView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println(evt.getActionCommand());
			}
		});

		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panel_3
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jXMonthView, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_3
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jXMonthView, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(458, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);

		jPnlAllClient.setLayout(gl_jPnlAllClient);

		this._THIS = this;

		_THIS.setupMenu();

		this.refreshRestaurantTables();
		this.refreshOpenCoupons();

	}

	private void setupMenu() {

		_THIS.menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);

		JMenuItem mntmRestaurante = new JMenuItem("Restaurante...");
		mntmRestaurante.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				SCGUIRestaurantDialogEdit.showDialog(SCGUIMainForm.this);
			}
		});
		mnCadastros.add(mntmRestaurante);

		JMenuItem mntmMesas = new JMenuItem("Mesas...");
		mnCadastros.add(mntmMesas);

		JMenuItem mntmProdutos = new JMenuItem("Produtos...");
		mnCadastros.add(mntmProdutos);

		JMenuItem mntmPlanoDeContas = new JMenuItem("Plano de Contas...");
		mnCadastros.add(mntmPlanoDeContas);

		JMenuItem mntmNewMenuItem = new JMenuItem("Centro de Custo...");
		mnCadastros.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Usu\u00E1rios...");
		mnCadastros.add(mntmNewMenuItem_1);

	}

	private void setupGrid(java.util.List<CouponProduct> couponProducts) {

		jTblItemsSelectedCoupon.setModel(new SCCouponsProductTableModel(
				couponProducts));

		jTblItemsSelectedCoupon.getColumnModel().getColumn(0)
				.setCellRenderer(SCCouponsTableModel.CENTER_ALIGN);
		jTblItemsSelectedCoupon.getColumnModel().getColumn(0)
				.setPreferredWidth(15);

		jTblItemsSelectedCoupon.getColumnModel().getColumn(1)
				.setCellRenderer(SCCouponsTableModel.CENTER_ALIGN);
		jTblItemsSelectedCoupon.getColumnModel().getColumn(1)
				.setPreferredWidth(41);

		jTblItemsSelectedCoupon.getColumnModel().getColumn(2)
				.setPreferredWidth(130);

		jTblItemsSelectedCoupon.getColumnModel().getColumn(3)
				.setCellRenderer(SCCouponsTableModel.RIGHT_ALIGN);
		jTblItemsSelectedCoupon.getColumnModel().getColumn(3)
				.setPreferredWidth(51);

	}

	@Override
	public void showMe(boolean modal) {

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(getDefaultToolKit().getScreenSize());
		// this.setResizable(false);
		this.setAlwaysOnTop(modal);
		this.setVisible(true);

	}
}
