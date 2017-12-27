/**
 * 
 */
package br.com.softctrl.pos.gui.main;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import br.com.softctrl.pos.util.Moeda;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 10:04:15 PM
 * 
 */
public final class SCCouponsTableModel extends
		javax.swing.table.AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6418862956909668080L;

	public static final DefaultTableCellRenderer LEF_ALIGN = new DefaultTableCellRenderer();
	public static final DefaultTableCellRenderer CENTER_ALIGN = new DefaultTableCellRenderer();
	public static final DefaultTableCellRenderer RIGHT_ALIGN = new DefaultTableCellRenderer();

	public static final NumberFormat nf10 = new DecimalFormat("0000000000");
	public static final NumberFormat nf05 = new DecimalFormat("00000");

	static {
		LEF_ALIGN.setHorizontalAlignment(SwingConstants.LEFT);
		CENTER_ALIGN.setHorizontalAlignment(SwingConstants.CENTER);
		RIGHT_ALIGN.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	private final String[] columnNames = new String[] { "Número", "No.Mesa",
			"HH:MM:SS", "SubTotal" };

	private java.util.List<br.com.softctrl.sys029.model.Coupon> coupons = null;

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	public SCCouponsTableModel(
			java.util.List<br.com.softctrl.sys029.model.Coupon> coupons) {

		super();
		this.coupons = coupons;

	}

	@Override
	public int getRowCount() {
		return this.coupons.size();
	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return (this.coupons.get(rowIndex).getFileNumber() == null ? this.coupons
					.get(rowIndex).getCode() : this.coupons.get(rowIndex)
					.getFileNumber());
		case 1:
			return nf05.format(this.coupons.get(rowIndex).getRestaurantTable()
					.getCode());
		case 2:
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			return sdf.format(this.coupons.get(rowIndex).getCreationDate());
		case 3:
			return Moeda.mascaraDinheiro(this.coupons.get(rowIndex)
					.getSubTotal(), Moeda.DINHEIRO_REAL);
		default:
			return null;
		}
	}
}
