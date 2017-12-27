/**
 * 
 */
package br.com.softctrl.util.string;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 9:23:10 AM
 * 
 */
public final class StringUtils {

	public static boolean isEmpty(String value) {
		return (value == null ? true : value.trim().length() == 0);
	}

}
