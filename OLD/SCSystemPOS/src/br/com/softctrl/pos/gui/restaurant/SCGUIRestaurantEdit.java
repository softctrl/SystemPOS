package br.com.softctrl.pos.gui.restaurant;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import javax.swing.SwingConstants;

public class SCGUIRestaurantEdit extends JDialog {

	private BindingGroup m_bindingGroup;
	private JPanel m_contentPane;
	private br.com.softctrl.sys029.model.Restaurant restaurant = new br.com.softctrl.sys029.model.Restaurant();
	private JTextField codeJTextField;
	private JSpinner customersCapacityJSpinner;
	private JLabel idJLabel;
	private JTextField nameJTextField;
	private JSpinner numberOfTablesJSpinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SCGUIRestaurantEdit dialog = new SCGUIRestaurantEdit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SCGUIRestaurantEdit() {
		setBounds(100, 100, 705, 186);
		m_contentPane = new JPanel();
		setContentPane(m_contentPane);
		m_contentPane.setLayout(null);

		JLabel codeLabel = new JLabel("C\u00F3digo:");
		codeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		codeLabel.setBounds(6, 40, 117, 16);
		m_contentPane.add(codeLabel);

		codeJTextField = new JTextField();
		codeJTextField.setBounds(137, 34, 87, 28);
		m_contentPane.add(codeJTextField);

		JLabel customersCapacityLabel = new JLabel("Capacidade Total:");
		customersCapacityLabel.setBounds(6, 108, 117, 16);
		m_contentPane.add(customersCapacityLabel);

		customersCapacityJSpinner = new JSpinner();
		customersCapacityJSpinner.setBounds(139, 102, 87, 28);
		m_contentPane.add(customersCapacityJSpinner);

		JLabel idLabel = new JLabel("Id:");
		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idLabel.setBounds(6, 6, 117, 16);
		m_contentPane.add(idLabel);

		idJLabel = new JLabel();
		idJLabel.setText("0000");
		idJLabel.setBounds(141, 6, 83, 16);
		m_contentPane.add(idJLabel);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(6, 74, 117, 16);
		m_contentPane.add(nameLabel);

		nameJTextField = new JTextField();
		nameJTextField.setBounds(139, 68, 451, 28);
		m_contentPane.add(nameJTextField);

		JLabel numberOfTablesLabel = new JLabel("NumberOfTables:");
		numberOfTablesLabel.setBounds(380, 108, 110, 16);
		m_contentPane.add(numberOfTablesLabel);

		numberOfTablesJSpinner = new JSpinner();
		numberOfTablesJSpinner.setBounds(503, 102, 87, 28);
		m_contentPane.add(numberOfTablesJSpinner);

		if (restaurant != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	protected BindingGroup initDataBindings() {
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.String> codeProperty = BeanProperty
				.create("code");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty = BeanProperty
				.create("text");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						restaurant, codeProperty, codeJTextField, textProperty);
		autoBinding.bind();
		//
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer> customersCapacityProperty = BeanProperty
				.create("customersCapacity");
		BeanProperty<javax.swing.JSpinner, java.lang.Object> valueProperty = BeanProperty
				.create("value");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer, javax.swing.JSpinner, java.lang.Object> autoBinding_1 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						restaurant, customersCapacityProperty,
						customersCapacityJSpinner, valueProperty);
		autoBinding_1.bind();
		//
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer> idProperty = BeanProperty
				.create("id");
		BeanProperty<javax.swing.JLabel, java.lang.String> textProperty_1 = BeanProperty
				.create("text");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer, javax.swing.JLabel, java.lang.String> autoBinding_2 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ, restaurant,
						idProperty, idJLabel, textProperty_1);
		autoBinding_2.bind();
		//
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.String> nameProperty = BeanProperty
				.create("name");
		BeanProperty<javax.swing.JTextField, java.lang.String> textProperty_2 = BeanProperty
				.create("text");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.String, javax.swing.JTextField, java.lang.String> autoBinding_3 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						restaurant, nameProperty, nameJTextField,
						textProperty_2);
		autoBinding_3.bind();
		//
		BeanProperty<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer> numberOfTablesProperty = BeanProperty
				.create("numberOfTables");
		BeanProperty<javax.swing.JSpinner, java.lang.Object> valueProperty_1 = BeanProperty
				.create("value");
		AutoBinding<br.com.softctrl.sys029.model.Restaurant, java.lang.Integer, javax.swing.JSpinner, java.lang.Object> autoBinding_4 = Bindings
				.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
						restaurant, numberOfTablesProperty,
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

	public br.com.softctrl.sys029.model.Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(
			br.com.softctrl.sys029.model.Restaurant newRestaurant) {
		setRestaurant(newRestaurant, true);
	}

	public void setRestaurant(
			br.com.softctrl.sys029.model.Restaurant newRestaurant,
			boolean update) {
		restaurant = newRestaurant;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (restaurant != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}

}
