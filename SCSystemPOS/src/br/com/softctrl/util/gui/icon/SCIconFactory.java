/**
 * 
 */
package br.com.softctrl.util.gui.icon;

import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 17, 2012 6:48:47 PM
 * 
 */
public class SCIconFactory {
	private static HashMap<String, ImageIcon> iconCashe = new HashMap<String, ImageIcon>();

	public static final ImageIcon BAIAO_DE_2_ICON = getSysIcon("baiao-de-dois.jpg");
	public static final ImageIcon TABLE_ICON_1 = getSysIcon("table-icon-1.png");

	public static ImageIcon getSysIcon(String iconName) {
		ImageIcon icon = iconCashe.get(iconName);
		if (icon == null) {
			try {
				icon = getIcon("/br/com/softctrl/images", iconName);
				iconCashe.put(iconName, icon);
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
		return icon;
	}

	public static ImageIcon getIcon(String pathIcon, String iconName) {
		ImageIcon icon = iconCashe.get(iconName);
		if (icon == null) {
			try {
				icon = new ImageIcon(SCIconFactory.class.getResource(String
						.format("%s/%s", pathIcon, iconName)));
				iconCashe.put(iconName, icon);
			} catch (Exception x) {
			}
		}
		return icon;
	}
}
