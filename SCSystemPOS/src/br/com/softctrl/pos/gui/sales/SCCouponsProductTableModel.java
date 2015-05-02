/**
 * 
 */
package br.com.softctrl.pos.gui.sales;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.softctrl.pos.util.Moeda;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 10:04:15 PM
 * 
 */
public final class SCCouponsProductTableModel extends
		javax.swing.table.AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6418862956909668080L;

	public static DefaultTableCellRenderer LEF_ALIGN = new DefaultTableCellRenderer();
	public static DefaultTableCellRenderer CENTER_ALIGN = new DefaultTableCellRenderer();
	public static DefaultTableCellRenderer RIGHT_ALIGN = new DefaultTableCellRenderer();

	public static final NumberFormat nf10 = new DecimalFormat("0000000000");
	public static final NumberFormat nf05 = new DecimalFormat("00000");

	static {
		LEF_ALIGN.setHorizontalAlignment(SwingConstants.LEFT);
		CENTER_ALIGN.setHorizontalAlignment(SwingConstants.CENTER);
		RIGHT_ALIGN.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	private final String[] columnNames = new String[] { "#", "Quant.", "Item",
			"Sub-Total" };

	private java.util.List<br.com.softctrl.sys029.model.CouponProduct> couponProducts = null;

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	public SCCouponsProductTableModel(
			final java.util.List<br.com.softctrl.sys029.model.CouponProduct> couponProducts) {

		super();
		this.couponProducts = couponProducts;

	}

	@Override
	public int getRowCount() {
		return this.couponProducts.size();
	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return (rowIndex + 1);
		case 1:
			return nf05.format(this.couponProducts.get(rowIndex).getQuantity());
		case 2:
			return this.couponProducts.get(rowIndex).getProduct()
					.getDescription();
		case 3:

			return Moeda.mascaraDinheiro(
					this.couponProducts
							.get(rowIndex)
							.getPriceTag()
							.multiply(
									this.couponProducts.get(rowIndex)
											.getQuantity()),
					Moeda.DINHEIRO_REAL);
		default:
			return null;
		}
	}
}
