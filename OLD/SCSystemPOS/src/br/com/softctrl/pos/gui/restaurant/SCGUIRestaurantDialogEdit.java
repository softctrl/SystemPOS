package br.com.softctrl.pos.gui.restaurant;

import br.com.softctrl.pos.gui.util.SCMessagesUtil;
import br.com.softctrl.sys029.model.Restaurant;
import br.com.softctrl.util.gui.dialog.SCGUIDialogApp;
import br.com.softctrl.util.gui.edit.ASCGUIDialogEdit;
import br.com.softctrl.util.gui.edit.ASCGUIPanelEdit;

public class SCGUIRestaurantDialogEdit extends ASCGUIDialogEdit<Restaurant> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 939828910527574514L;

	public static void main(String[] args) {

		SCGUIRestaurantPanelEdit editPanel = new SCGUIRestaurantPanelEdit();

		SCGUIRestaurantDialogEdit d = new SCGUIRestaurantDialogEdit(editPanel);

		d.setEntity(d.entityDAO.findById(1));
		d.initDataBindings();
		d.showMe(false);

	}

	protected SCGUIRestaurantDialogEdit(SCGUIRestaurantPanelEdit editPanel) {

		super(editPanel, Restaurant.class);
		this.setBounds(100, 100, 610, 230);
		this.setResizable(false);

	}

	public SCGUIRestaurantDialogEdit(SCGUIRestaurantPanelEdit editPanel,
			SCGUIDialogApp owner) {

		super(editPanel, Restaurant.class, owner);
		this.setBounds(100, 100, 610, 230);
		this.setResizable(false);

	}

	public static void showDialog(SCGUIDialogApp owner) {

		SCGUIRestaurantPanelEdit editPanel = new SCGUIRestaurantPanelEdit();
		SCGUIRestaurantDialogEdit d = new SCGUIRestaurantDialogEdit(editPanel,
				owner );
		d.setEntity(d.entityDAO.findById(1));
		d.initDataBindings();
		d.showMe(true);

	}

	@Override
	public void showMe(boolean modal) {
		this.setVisible(true);
	}

	@Override
	public boolean forInsert() {
		return this.getEntity().getId() == null;
	}

	@Override
	public boolean beforeRemove() {
		if (this.getEntity().getCode().equals("100")) {
			SCMessagesUtil.showError("Imposs’vel remover restaurante padr‹o.");
			return false;
		}
		return super.beforeRemove();
	}

	@Override
	public boolean canRemove() {
		return this.getEntity().getId() != null;
	}

}
