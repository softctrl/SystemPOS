/**
 * 
 */
package br.com.softctrl.pos.gui.account;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

import br.com.softctrl.sys029.model.AccountingPlan;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import br.com.softctrl.sys029.model.enumeration.AccountType;
import br.com.softctrl.util.gui.edit.ASCGUIPanelEdit;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 19, 2012 2:19:57 PM
 * 
 */
public class SCGUIAccountingPlanPanelEdit extends
		ASCGUIPanelEdit<AccountingPlan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7334518167829109162L;
	private JTextField codeJTextField;
	private JTextField descriptionJTextField;
	private JLabel idJLabel;
	private JCheckBox isAccountRootJCheckBox;
	private JComboBox accountTypeJComboBox;

	public SCGUIAccountingPlanPanelEdit() {
		super();
		this.setLayout(null);

		JLabel codeLabel = new JLabel("Code:");
		codeLabel.setBounds(47, 38, 36, 16);
		this.add(codeLabel);

		codeJTextField = new JTextField();
		codeJTextField.setBounds(95, 32, 171, 28);
		this.add(codeJTextField);

		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setBounds(6, 66, 77, 16);
		this.add(descriptionLabel);

		descriptionJTextField = new JTextField();
		descriptionJTextField.setBounds(95, 60, 338, 28);
		this.add(descriptionJTextField);

		JLabel idLabel = new JLabel("Id:");
		idLabel.setBounds(67, 10, 16, 16);
		this.add(idLabel);

		idJLabel = new JLabel();
		idJLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		idJLabel.setBounds(95, 10, 82, 16);
		this.add(idJLabel);

		isAccountRootJCheckBox = new JCheckBox();
		isAccountRootJCheckBox.setText("Conta Raiz");
		isAccountRootJCheckBox.setBounds(334, 34, 99, 23);
		this.add(isAccountRootJCheckBox);

		accountTypeJComboBox = new JComboBox();
		accountTypeJComboBox.setModel(new DefaultComboBoxModel(AccountType
				.values()));
		accountTypeJComboBox.setBounds(300, 6, 133, 27);
		add(accountTypeJComboBox);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(252, 10, 36, 16);
		add(lblTipo);

		if (this.getEntity() != null) {
			this.initDataBindings();
		}

	}

	@Override
	public BindingGroup initDataBindings() {
		BeanProperty<AccountingPlan, String> codeProperty = BeanProperty
				.create("code");
		BeanProperty<JTextField, String> textProperty = BeanProperty
				.create("text");
		AutoBinding<AccountingPlan, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, getEntity(),
						codeProperty, codeJTextField, textProperty);
		autoBinding.bind();
		//
		BeanProperty<AccountingPlan, String> descriptionProperty = BeanProperty
				.create("description");
		BeanProperty<JTextField, String> textProperty_1 = BeanProperty
				.create("text");
		AutoBinding<AccountingPlan, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, getEntity(),
						descriptionProperty, descriptionJTextField,
						textProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<AccountingPlan, Integer> idProperty = BeanProperty
				.create("id");
		BeanProperty<JLabel, String> textProperty_2 = BeanProperty
				.create("text");
		AutoBinding<AccountingPlan, Integer, JLabel, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ, getEntity(),
						idProperty, idJLabel, textProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<AccountingPlan, Boolean> isAccountRootProperty = BeanProperty
				.create("isAccountRoot");
		BeanProperty<JCheckBox, Boolean> selectedProperty = BeanProperty
				.create("selected");
		AutoBinding<AccountingPlan, Boolean, JCheckBox, Boolean> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, getEntity(),
						isAccountRootProperty, isAccountRootJCheckBox,
						selectedProperty);
		autoBinding_3.bind();
		//
		BeanProperty<AccountingPlan, AccountType> accountingPlanBeanProperty = BeanProperty
				.create("accountType");
		BeanProperty<JComboBox, Object> jComboBoxBeanProperty = BeanProperty
				.create("selectedItem");
		AutoBinding<AccountingPlan, AccountType, JComboBox, Object> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ_WRITE, getEntity(),
						accountingPlanBeanProperty, accountTypeJComboBox,
						jComboBoxBeanProperty);
		autoBinding_4.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		//
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_4);
		return bindingGroup;
	}
}
