package br.com.softctrl.pos.gui.main;

import java.awt.Component;
import java.text.Format;

import javax.swing.AbstractCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

//Um editor baseado no FormattedTextField.  
public class FormattedCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7842720907720881810L;
	private JFormattedTextField tf;

	// Construtores úteis
	public FormattedCellEditor(Format format) {
		tf = new JFormattedTextField(format);
	}

	public FormattedCellEditor(JFormattedTextField.AbstractFormatter formatter) {
		tf = new JFormattedTextField(formatter);
	}

	public FormattedCellEditor(
			JFormattedTextField.AbstractFormatterFactory factory) {
		tf = new JFormattedTextField(factory);
	}

	// Esse método é chamado pelo JTable. Ele te diz que linha, coluna e valor o
	// usuário está tentando editar.
	// E você retorna ao JTable o editor, com o valor em questão já preenchido.
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int rowIndex, int vColIndex) {
		tf.setValue(value);
		return tf;
	}

	// Esse método é chamado quando a edição termina.
	// Ele deve retornar o novo valor que será gravado na célula.
	public Object getCellEditorValue() {
		return tf.getValue();
	}
}