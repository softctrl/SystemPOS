/**
 * 
 */
package br.com.softctrl.util.gui.edit;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 19, 2012 2:11:49 PM
 * 
 */
public interface ISCEntityEdit<E extends Object> extends java.io.Serializable {

	void setEntity(E entity);

	E getEntity();

}
