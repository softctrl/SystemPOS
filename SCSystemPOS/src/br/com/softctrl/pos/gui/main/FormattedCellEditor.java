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

	// Construtores �teis
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

	// Esse m�todo � chamado pelo JTable. Ele te diz que linha, coluna e valor o
	// usu�rio est� tentando editar.
	// E voc� retorna ao JTable o editor, com o valor em quest�o j� preenchido.
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int rowIndex, int vColIndex) {
		tf.setValue(value);
		return tf;
	}

	// Esse m�todo � chamado quando a edi��o termina.
	// Ele deve retornar o novo valor que ser� gravado na c�lula.
	public Object getCellEditorValue() {
		return tf.getValue();
	}
}