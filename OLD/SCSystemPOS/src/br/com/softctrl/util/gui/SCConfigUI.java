package br.com.softctrl.util.gui;

import java.awt.Font;

import javax.swing.UIManager;

/**
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 17, 2012 6:45:28 PM
 * 
 */
public class SCConfigUI {

	public final static Font buttonFont = UIManager.getFont("Button.font")
			.deriveFont(Font.BOLD, 12);
	public final static Font largeFont = UIManager.getFont("Button.font")
			.deriveFont(Font.BOLD, 16);

	public SCConfigUI() {
		super();
	}

	public static Font getButtonFont() {
		return buttonFont;
	}

}
