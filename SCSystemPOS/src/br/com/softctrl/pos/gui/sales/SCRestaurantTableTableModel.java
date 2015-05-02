/**
 * 
 */
package br.com.softctrl.pos.gui.sales;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 17, 2012 3:03:37 PM
 * 
 */
public final class SCRestaurantTableTableModel extends
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

	private final String[] columnNames = new String[] { "Número", "Descrição"};

	private java.util.List<br.com.softctrl.sys029.model.RestaurantTable> coupons = null;

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	public SCRestaurantTableTableModel(
			java.util.List<br.com.softctrl.sys029.model.RestaurantTable> coupons) {

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
			return this.coupons.get(rowIndex).getCode();
		case 1:
			return this.coupons.get(rowIndex).getDescription();
		default:
			return null;
		}
	}
}
