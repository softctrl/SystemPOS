/**
 * 
 */
package br.com.softctrl.util.gui.edit;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 19, 2012 2:11:49 PM
 * 
 */
public interface ISCGUIPanelEdit<E extends Object> extends ISCEntityEdit<E> {

	org.jdesktop.beansbinding.BindingGroup initDataBindings();

}
