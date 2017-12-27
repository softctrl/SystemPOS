package br.com.softctrl.pos.gui.financial;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingx.JXDatePicker;

import br.com.softctrl.sys029.model.CashFLow;
import br.com.softctrl.util.gui.SCTimestampDateConverter;

public class AuxBinding extends JDialog {

	private BindingGroup m_bindingGroup;
	private JPanel m_contentPane;
	private br.com.softctrl.sys029.model.CashFLow cashFLow = null;// new
																	// br.com.softctrl.sys029.model.CashFLow();
	private JTextField codeJTextField;
	private JTextField descriptionJTextField;
	private JTextField documentNumberJTextField;
	private JLabel idJLabel;
	private JTextField textField;
	private JCheckBox jCkbCanceled;
	private JXDatePicker jDtpAccountDate = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AuxBinding dialog = new AuxBinding();
			CashFLow newCashFLow = new CashFLow();
			newCashFLow.setId(10);
			newCashFLow.setAccountDate(new Timestamp(new Date().getTime()));
			newCashFLow.setAmount(BigDecimal.TEN);
			newCashFLow.setDiscount(BigDecimal.TEN);
			newCashFLow.setCode("123123123213123");
			newCashFLow.setDescription("Description");
			newCashFLow.setIsCanceled(true);
			newCashFLow.setIsPaid(true);

			dialog.setCashFLow(newCashFLow);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AuxBinding() {
		setBounds(100, 100, 847, 529);
		m_contentPane = new JPanel();
		setContentPane(m_contentPane);
		m_contentPane.setLayout(null);

		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setBounds(101, 34, 36, 16);
		m_contentPane.add(codeLabel);

		codeJTextField = new JTextField();
		codeJTextField.setBounds(142, 28, 119, 28);
		m_contentPane.add(codeJTextField);

		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setBounds(60, 68, 77, 16);
		m_contentPane.add(descriptionLabel);

		descriptionJTextField = new JTextField();
		descriptionJTextField.setBounds(142, 62, 606, 28);
		m_contentPane.add(descriptionJTextField);

		JLabel documentNumberLabel = new JLabel("DocumentNumber:");
		documentNumberLabel.setBounds(6, 148, 119, 16);
		m_contentPane.add(documentNumberLabel);

		documentNumberJTextField = new JTextField();
		documentNumberJTextField.setBounds(142, 142, 159, 28);
		m_contentPane.add(documentNumberJTextField);

		JLabel idLabel = new JLabel("Id:");
		idLabel.setBounds(108, 6, 16, 16);
		m_contentPane.add(idLabel);

		idJLabel = new JLabel();
		idJLabel.setBounds(139, 6, 16, 16);
		m_contentPane.add(idJLabel);

		textField = new JTextField();
		textField.setBounds(142, 102, 107, 28);
		m_contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(258, 102, 46, 29);
		m_contentPane.add(btnNewButton);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(304, 103, 201, 27);
		m_contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(517, 108, 61, 16);
		m_contentPane.add(lblNewLabel);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(589, 104, 159, 27);
		m_contentPane.add(comboBox_1);

		JXDatePicker datePicker = new JXDatePicker();
		datePicker.setBounds(583, 28, 165, 28);
		m_contentPane.add(datePicker);

		jDtpAccountDate = new JXDatePicker();
		jDtpAccountDate.setBounds(583, 142, 165, 28);
		m_contentPane.add(jDtpAccountDate);

		jCkbCanceled = new JCheckBox("Cancelado");
		jCkbCanceled.setBounds(589, 2, 97, 23);
		m_contentPane.add(jCkbCanceled);

		if (cashFLow != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	public br.com.softctrl.sys029.model.CashFLow getCashFLow() {
		return cashFLow;
	}

	public void setCashFLow(br.com.softctrl.sys029.model.CashFLow newCashFLow) {
		setCashFLow(newCashFLow, true);
	}

	public void setCashFLow(br.com.softctrl.sys029.model.CashFLow newCashFLow,
			boolean update) {
		cashFLow = newCashFLow;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (cashFLow != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}

	protected BindingGroup initDataBindings() {
		BeanProperty<CashFLow, String> codeProperty = BeanProperty
				.create("code");
		BeanProperty<JTextField, String> textProperty = BeanProperty
				.create("text");
		AutoBinding<CashFLow, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, cashFLow,
						codeProperty, codeJTextField, textProperty);
		autoBinding.bind();
		//
		BeanProperty<CashFLow, String> descriptionProperty = BeanProperty
				.create("description");
		BeanProperty<JTextField, String> textProperty_1 = BeanProperty
				.create("text");
		AutoBinding<CashFLow, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, cashFLow,
						descriptionProperty, descriptionJTextField,
						textProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<CashFLow, String> documentNumberProperty = BeanProperty
				.create("documentNumber");
		BeanProperty<JTextField, String> textProperty_2 = BeanProperty
				.create("text");
		AutoBinding<CashFLow, String, JTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, cashFLow,
						documentNumberProperty, documentNumberJTextField,
						textProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<CashFLow, Integer> idProperty = BeanProperty.create("id");
		BeanProperty<JLabel, String> textProperty_3 = BeanProperty
				.create("text");
		AutoBinding<CashFLow, Integer, JLabel, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ, cashFLow, idProperty,
						idJLabel, textProperty_3);
		autoBinding_3.bind();
		//
		BeanProperty<CashFLow, Boolean> isCanceledProperty = BeanProperty
				.create("isCanceled");
		BeanProperty<JCheckBox, Boolean> selectedProperty = BeanProperty
				.create("selected");
		AutoBinding<CashFLow, Boolean, JCheckBox, Boolean> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, cashFLow,
						isCanceledProperty, jCkbCanceled, selectedProperty);
		autoBinding_4.bind();
		//
		BeanProperty<CashFLow, Timestamp> cashFLowBeanProperty = BeanProperty
				.create("accountDate");
		BeanProperty<JXDatePicker, Date> jXDatePickerBeanProperty = BeanProperty
				.create("date");
		AutoBinding<CashFLow, Timestamp, JXDatePicker, Date> autoBinding_5 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, cashFLow,
						cashFLowBeanProperty, jDtpAccountDate,
						jXDatePickerBeanProperty);
		autoBinding_5.setConverter(new SCTimestampDateConverter());
		autoBinding_5.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		//
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_4);
		bindingGroup.addBinding(autoBinding_5);
		return bindingGroup;
	}
}
