package br.com.softctrl.pos.gui.sales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import br.com.softctrl.pos.control.ProductController;
import br.com.softctrl.pos.control.main.CouponController;
import br.com.softctrl.pos.control.main.CouponProductController;
import br.com.softctrl.pos.gui.lov.SCGUIListOfValue;
import br.com.softctrl.pos.gui.main.SCCouponsTableModel;
import br.com.softctrl.pos.gui.util.SCMessagesUtil;
import br.com.softctrl.pos.util.Moeda;
import br.com.softctrl.sys029.model.Coupon;
import br.com.softctrl.sys029.model.CouponProduct;
import br.com.softctrl.sys029.model.Product;
import br.com.softctrl.sys029.model.RestaurantTable;
import br.com.softctrl.sys029.model.dao.EntityDAO;
import br.com.softctrl.util.gui.icon.SCIconFactory;
import br.com.softctrl.util.gui.layout.WrapLayout;
import javax.swing.JFormattedTextField;

public class SCGUISales extends br.com.softctrl.util.gui.dialog.SCGUIDialogApp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8360015584526666120L;
	private final JPanel jPnlAll = new JPanel();

	private SCGUISales _THIS;

	private final MatteBorder matteBorder = new MatteBorder(1, 0, 1, 0,
			(Color) new Color(0, 0, 0));

	private JTable jTblCouponItems = null;
	private JPanel jPnlProductList = null;

	private JButton jBtnchangeTableNumber = null;

	private JButton jBtnPlus = null;
	private JButton jBtnMinus = null;
	private JButton jBtnDelete = null;
	private JButton jBtnCancel = null;
	private JButton jBtnPrint = null;
	private JButton jBtnDetail = null;

	private JButton jBtnPersist = null;

	private JFormattedTextField jFTxCouponSubTotal = null;
	private JFormattedTextField jFTxDiscount = null;
	private JFormattedTextField jFTxCouponValue = null;

	private Coupon coupon = null;

	private final ProductController controller = new ProductController();
	private final CouponController couponController = new CouponController();
	private final CouponProductController couponProductController = new CouponProductController();

	private final ActionListener changeTableActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent acte) {
			EntityDAO<RestaurantTable> tableDAO = new EntityDAO<RestaurantTable>(
					RestaurantTable.class);
			final List<RestaurantTable> tables = tableDAO.findAll();
			SCGUIListOfValue lov = SCGUIListOfValue.newInstance(tables, _THIS,
					new SCRestaurantTableTableModel(tables));
			lov._show();
			if (lov.getValue() != null) {
				_THIS.coupon.setRestaurantTable((RestaurantTable) lov
						.getValue());

				_THIS.couponController.save(_THIS.coupon);
				((JButton) acte.getSource()).setText(_THIS.coupon
						.getRestaurantTable().getDescription());
			}
			System.out.println(lov.getValue());
		}
	};

	private final ActionListener addProductActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent acte) {
			if (coupon.getId() == null)
				couponController.save(coupon);
			SCProductButton pb = (SCProductButton) acte.getSource();
			CouponProduct cp = coupon.addProduct(pb.getProduct());
			couponProductController.save(cp);
			refreshCouponsInformation();
		}
	};

	private final ActionListener plusCouponItemActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent acte) {
			int idx = jTblCouponItems.getSelectedRow();
			if (idx != -1 && jTblCouponItems.getRowCount() > 0) {
				CouponProduct cp = coupon.getCouponProducts().get(idx);
				cp.setQuantity(cp.getQuantity().add(BigDecimal.ONE));
				refreshCouponsInformation();
			}
		}
	};

	private final ActionListener minusCouponItemActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent acte) {
			int idx = jTblCouponItems.getSelectedRow();
			if (idx != -1 && jTblCouponItems.getRowCount() > 0) {
				CouponProduct cp = coupon.getCouponProducts().get(idx);
				int compare = BigDecimal.ZERO.compareTo(cp.getQuantity()
						.subtract(BigDecimal.ONE));
				if (compare == -1) {
					cp.setQuantity(cp.getQuantity().subtract(BigDecimal.ONE));
				} else {
					couponProductController.remove(cp);
					coupon.getCouponProducts().remove(cp);
					jTblCouponItems.clearSelection();
				}
				refreshCouponsInformation();
			}
		}
	};

	private final ActionListener deleteCouponItemActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent acte) {

			List<CouponProduct> cps = new ArrayList<CouponProduct>();
			for (int idx : jTblCouponItems.getSelectedRows()) {
				cps.add(coupon.getCouponProducts().get(idx));
			}

			for (CouponProduct cp : cps) {
				couponProductController.remove(cp);
				coupon.getCouponProducts().remove(cp);
			}
			jTblCouponItems.clearSelection();
			refreshCouponsInformation();

		}
	};

	private final ActionListener persistCouponActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent acte) {
			if (_THIS.coupon.getCouponProducts().size() > 0) {
				_THIS.couponController.save(_THIS.coupon);
			} else {
				_THIS.couponController.cancel();
			}
			// _THIS.refreshCouponsInformation();
			_THIS.close();
		}

	};

	private final ActionListener cancelCouponActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent acte) {
			if (_THIS.coupon == null || _THIS.coupon.getCode() == null) {
				_THIS.couponController.cancel();
			} else {
				_THIS.couponController.delete(_THIS.coupon);
			}
			_THIS.close();
		}

	};

	private final ActionListener showCouponDetailActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent acte) {
			if (_THIS.coupon.getCouponProducts().size() > 0) {
				SCMessagesUtil.showMessage(_THIS.coupon.toString());
			}
		}

	};

	private void refreshCouponsInformation() {

		jFTxCouponSubTotal.setText(Moeda.mascaraDinheiro(
				this.coupon.getSubTotal(), Moeda.DINHEIRO_REAL));

		jFTxDiscount.setText(Moeda.mascaraDinheiro(
				this.coupon.getDiscountTotal(), Moeda.DINHEIRO_REAL));

		jFTxCouponSubTotal.revalidate();
		jFTxCouponSubTotal.repaint();
		jTblCouponItems.revalidate();
		jTblCouponItems.repaint();

	}

	private void setupProductsVisualization() {
		for (Product p : controller.findAll()) {

			jPnlProductList.add(new SCProductButton(p, p.getDescription(),
					SCIconFactory.BAIAO_DE_2_ICON, addProductActionListener));

		}
	}

	private void setupGrid() {

		jTblCouponItems.setModel(new SCCouponsProductTableModel(this.coupon
				.getCouponProducts()));

		jTblCouponItems.getColumnModel().getColumn(0)
				.setCellRenderer(SCCouponsTableModel.CENTER_ALIGN);
		jTblCouponItems.getColumnModel().getColumn(0).setPreferredWidth(15);

		jTblCouponItems.getColumnModel().getColumn(1)
				.setCellRenderer(SCCouponsTableModel.CENTER_ALIGN);
		jTblCouponItems.getColumnModel().getColumn(1).setPreferredWidth(41);

		jTblCouponItems.getColumnModel().getColumn(2).setPreferredWidth(130);

		jTblCouponItems.getColumnModel().getColumn(3)
				.setCellRenderer(SCCouponsTableModel.RIGHT_ALIGN);
		jTblCouponItems.getColumnModel().getColumn(3).setPreferredWidth(51);

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SCGUISales dialog = SCGUISales.newInstance(null, new Coupon());
			dialog.setupProductsVisualization();
			dialog.setupGrid();
			dialog.showMe(false);
		} catch (Exception e) {
			SCMessagesUtil.showError("Erro na aplicação\n" + e.getMessage());
		}
	}

	public static SCGUISales newInstance(
			br.com.softctrl.util.gui.dialog.SCGUIDialogApp owner, Coupon coupon) {

		SCGUISales gui = new SCGUISales(owner,
				Dialog.ModalityType.DOCUMENT_MODAL);
		gui.jBtnchangeTableNumber.setText(coupon.getRestaurantTable()
				.getDescription());
		gui.jBtnchangeTableNumber.setIcon(SCIconFactory.TABLE_ICON_1);
		gui.coupon = coupon;
		gui.refreshCouponsInformation();
		return gui;

	}

	/**
	 * Create the dialog.
	 */
	private SCGUISales(Dialog owner, Dialog.ModalityType modalityType) {
		super(owner, modalityType);
		this.initialize();
	}

	public SCGUISales() {
		super();
		this.initialize();
	}

	private void initialize() {

		setBounds(100, 100, 950, 669);
		getContentPane().setLayout(new BorderLayout());
		jPnlAll.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(jPnlAll, BorderLayout.CENTER);
		jPnlAll.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		jPnlAll.add(panel_1, BorderLayout.EAST);

		JPanel panel = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 363,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 625,
								Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_5
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 351,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_5
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 317,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		panel_7.setLayout(new BorderLayout(0, 0));

		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10, BorderLayout.NORTH);

		JPanel panel_11 = new JPanel();
		panel_11.setBorder(matteBorder);
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(gl_panel_10.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_10
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 339,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel_10.setVerticalGroup(gl_panel_10.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_panel_10
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(panel_11, GroupLayout.PREFERRED_SIZE,
								102, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_11.setLayout(null);

		jFTxCouponSubTotal = new JFormattedTextField();
		jFTxCouponSubTotal.setEnabled(false);
		jFTxCouponSubTotal.setEditable(false);
		jFTxCouponSubTotal.setBounds(152, 6, 181, 28);
		jFTxCouponSubTotal.setBorder(matteBorder);
		jFTxCouponSubTotal.setHorizontalAlignment(JTextField.RIGHT);

		panel_11.add(jFTxCouponSubTotal);
		jFTxCouponSubTotal.setColumns(10);

		JLabel lblSubtotal = new JLabel(getString("SUB_TOTAL"));
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC,
				15));
		lblSubtotal.setBounds(6, 11, 134, 16);
		panel_11.add(lblSubtotal);

		JLabel lblSubtotal_1 = new JLabel(getString("DISCOUNT"));
		lblSubtotal_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal_1.setFont(new Font("Lucida Grande",
				Font.BOLD | Font.ITALIC, 15));
		lblSubtotal_1.setBounds(6, 43, 134, 16);
		panel_11.add(lblSubtotal_1);

		jFTxDiscount = new JFormattedTextField();
		jFTxDiscount.setColumns(10);
		jFTxDiscount.setBorder(matteBorder);
		jFTxDiscount.setBounds(152, 38, 181, 28);
		jFTxDiscount.setHorizontalAlignment(JTextField.RIGHT);
		panel_11.add(jFTxDiscount);

		JLabel lblTotal = new JLabel(String.format("%s%s", getString("TOTAL"),
				":"));
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblTotal.setBounds(6, 76, 134, 16);
		panel_11.add(lblTotal);

		jFTxCouponValue = new JFormattedTextField();
		jFTxCouponValue.setEnabled(false);
		jFTxCouponValue.setEditable(false);
		jFTxCouponValue.setColumns(10);
		jFTxCouponValue.setBorder(matteBorder);
		jFTxCouponValue.setBounds(152, 71, 181, 28);
		jFTxCouponValue.setHorizontalAlignment(JTextField.RIGHT);
		panel_11.add(jFTxCouponValue);
		panel_10.setLayout(gl_panel_10);

		JPanel panel_12 = new JPanel();
		panel_7.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new GridLayout(4, 1, 0, 0));

		JPanel panel_14 = new JPanel();
		panel_12.add(panel_14);
		panel_14.setLayout(new GridLayout(1, 0, 0, 0));

		jBtnPlus = new JButton(SCIconFactory.getSysIcon("add_user_32.png"));
		jBtnPlus.addActionListener(plusCouponItemActionListener);
		panel_14.add(jBtnPlus);

		jBtnMinus = new JButton(SCIconFactory.getSysIcon("minus_32.png"));
		jBtnMinus.addActionListener(minusCouponItemActionListener);
		panel_14.add(jBtnMinus);

		jBtnDelete = new JButton(SCIconFactory.getSysIcon("delete_32.png"));

		jBtnDelete.addActionListener(deleteCouponItemActionListener);
		panel_14.add(jBtnDelete);

		JPanel panel_15 = new JPanel();
		panel_12.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));

		JButton jBtnPrior = new JButton("A");
		panel_15.add(jBtnPrior);

		JButton jBtnNext = new JButton("V");
		panel_15.add(jBtnNext);

		JPanel panel_16 = new JPanel();
		panel_12.add(panel_16);
		panel_16.setLayout(new GridLayout(1, 0, 0, 0));

		JButton jBtnPayout = new JButton(getString("PAY_OUT"));
		panel_16.add(jBtnPayout);

		JPanel panel_13 = new JPanel();
		panel_12.add(panel_13);
		panel_13.setLayout(new GridLayout(1, 0, 0, 0));

		jBtnCancel = new JButton(getString("CANCEL"),
				SCIconFactory.getSysIcon("cancel_32.png"));
		jBtnCancel.addActionListener(cancelCouponActionListener);
		panel_13.add(jBtnCancel);

		jBtnPersist = new JButton(getString("SAVE_COUPON"),
				SCIconFactory.getSysIcon("settle_ticket_32.png"));

		jBtnPersist.addActionListener(persistCouponActionListener);
		panel_13.add(jBtnPersist);
		panel_5.setLayout(gl_panel_5);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab(getString("COUPON"), null, scrollPane_1, null);

		jTblCouponItems = new JTable();
		scrollPane_1.setViewportView(jTblCouponItems);

		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		jPnlAll.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.SOUTH);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(null);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_3
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 278,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_3
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 129,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		panel_4.setLayout(new GridLayout(2, 3, 0, 0));

		jBtnDetail = new JButton("--Resumo",
				SCIconFactory.getSysIcon("settle_ticket_32.png"));
		jBtnDetail.addActionListener(showCouponDetailActionListener);
		panel_4.add(jBtnDetail);

		jBtnPrint = new JButton(getString("PRINT"),
				SCIconFactory.getSysIcon("print_32.png"));

		panel_4.add(jBtnPrint);

		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));

		jBtnchangeTableNumber = new JButton("Mesa n\u00FAmero 5");
		jBtnchangeTableNumber.addActionListener(changeTableActionListener);
		panel_8.add(jBtnchangeTableNumber);

		JButton jBtnChangeGuestNumber = new JButton("5 convidados");
		panel_8.add(jBtnChangeGuestNumber);

		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));

		JButton button_1 = new JButton("????");
		panel_9.add(button_1);

		JButton btnNewButton_2 = new JButton("???");
		panel_9.add(btnNewButton_2);
		panel_3.setLayout(gl_panel_3);

		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane_3, BorderLayout.CENTER);

		JPanel panel_6 = new JPanel();
		tabbedPane_3.addTab("Produtos", null, panel_6, null);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_17 = new JPanel();
		panel_6.add(panel_17, BorderLayout.SOUTH);

		JPanel panel_18 = new JPanel();
		GroupLayout gl_panel_17 = new GroupLayout(panel_17);
		gl_panel_17.setHorizontalGroup(gl_panel_17.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_17
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_18, GroupLayout.DEFAULT_SIZE, 532,
								Short.MAX_VALUE).addContainerGap()));
		gl_panel_17.setVerticalGroup(gl_panel_17.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panel_17
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(panel_18, GroupLayout.PREFERRED_SIZE,
								120, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		panel_17.setLayout(gl_panel_17);

		JScrollPane scrollPane = new JScrollPane();
		panel_6.add(scrollPane, BorderLayout.CENTER);

		jPnlProductList = new JPanel();
		WrapLayout wl_jPnlPageViewTables = new WrapLayout();
		wl_jPnlPageViewTables.setAlignment(FlowLayout.LEFT);
		jPnlProductList.setLayout(wl_jPnlPageViewTables);

		scrollPane.setViewportView(jPnlProductList);

		this._THIS = this;

	}

	@Override
	public void showMe(boolean modal) {
		try {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setSize(getDefaultToolKit().getScreenSize());
			// this.setResizable(false);
			this.setAlwaysOnTop(modal);
			this.setupProductsVisualization();
			this.setupGrid();
			this.repaint();
			this.setVisible(true);
		} catch (Exception e) {
			SCMessagesUtil.showError(_THIS,
					"Erro na aplicação\n" + e.getLocalizedMessage());

		}
	}

	@Override
	public void close() {
		super.close();
	}
}
