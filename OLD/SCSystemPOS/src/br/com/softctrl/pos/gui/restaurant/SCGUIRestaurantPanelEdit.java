/**
 * 
 */
package br.com.softctrl.pos.gui.restaurant;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

import br.com.softctrl.sys029.model.Restaurant;
import br.com.softctrl.util.gui.edit.ASCGUIPanelEdit;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 19, 2012 5:03:11 PM
 * 
 */
public class SCGUIRestaurantPanelEdit extends ASCGUIPanelEdit<Restaurant> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2364508559709703763L;
	private JTextField codeJTextField;
	private JSpinner customersCapacityJSpinner;
	private JLabel idJLabel;
	private JTextField nameJTextField;
	private JSpinner numberOfTablesJSpinner;

	public SCGUIRestaurantPanelEdit() {
		super();
		this.setLayout(null);

		JLabel codeLabel = new JLabel("C\u00F3digo:");
		codeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		codeLabel.setBounds(6, 40, 117, 16);
		this.add(codeLabel);

		codeJTextField = new JTextField();
		codeJTextField.setBounds(137, 34, 87, 28);
		this.add(codeJTextField);

		JLabel customersCapacityLabel = new JLabel("Capacidade Total:");
		customersCapacityLabel.setBounds(6, 108, 117, 16);
		this.add(customersCapacityLabel);

		customersCapacityJSpinner = new JSpinner();
		customersCapacityJSpinner.setBounds(139, 102, 87, 28);
		this.add(customersCapacityJSpinner);

		JLabel idLabel = new JLabel("Id:");
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setBounds(6, 6, 117, 16);
		this.add(idLabel);

		idJLabel = new JLabel();
		idJLabel.setText("0000");
		idJLabel.setBounds(141, 6, 83, 16);
		this.add(idJLabel);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(6, 74, 117, 16);
		this.add(nameLabel);

		nameJTextField = new JTextField();
		nameJTextField.setBounds(139, 68, 451, 28);
		this.add(nameJTextField);

		JLabel numberOfTablesLabel = new JLabel("Total de Mesas:");
		numberOfTablesLabel.setBounds(388, 108, 98, 16);
		this.add(numberOfTablesLabel);

		numberOfTablesJSpinner = new JSpinner();
		numberOfTablesJSpinner.setBounds(503, 102, 87, 28);
		this.add(numberOfTablesJSpinner);

		if (this.getEntity() != null) {
			initDataBindings();
		}

	}

	@Override
	public BindingGroup initDataBindings() {
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.String> codeProperty = BeanProperty
				.create("code");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty = BeanProperty
				.create("text");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						getEntity(), codeProperty, codeJTextField, textProperty);
		autoBinding.bind();
		//
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer> customersCapacityProperty = BeanProperty
				.create("customersCapacity");
		BeanProperty<javax.swing.JSpinner, java.lang.Object> valueProperty = BeanProperty
				.create("value");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer, javax.swing.JSpinner, java.lang.Object> autoBinding_1 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						getEntity(), customersCapacityProperty,
						customersCapacityJSpinner, valueProperty);
		autoBinding_1.bind();
		//
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer> idProperty = BeanProperty
				.create("id");
		BeanProperty<javax.swing.JLabel, java.lang.String> textProperty_1 = BeanProperty
				.create("text");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer, javax.swing.JLabel, java.lang.String> autoBinding_2 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ,
						getEntity(), idProperty, idJLabel, textProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.String> nameProperty = BeanProperty
				.create("name");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_2 = BeanProperty
				.create("text");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_3 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						getEntity(), nameProperty, nameJTextField,
						textProperty_2);
		autoBinding_3.bind();
		//
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer> numberOfTablesProperty = BeanProperty
				.create("numberOfTables");
		BeanProperty<javax.swing.JSpinner, java.lang.Object> valueProperty_1 = BeanProperty
				.create("value");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer, javax.swing.JSpinner, java.lang.Object> autoBinding_4 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						getEntity(), numberOfTablesProperty,
						numberOfTablesJSpinner, valueProperty_1);
		autoBinding_4.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_4);
		//
		return bindingGroup;
	}

}
