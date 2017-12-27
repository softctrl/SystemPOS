/**
 * 
 */
package br.com.softctrl.util.gui.edit;

import org.jdesktop.beansbinding.BindingGroup;


/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 19, 2012 2:14:29 PM
 * 
 */
@SuppressWarnings("serial")
public abstract class ASCGUIPanelEdit<E extends Object> extends
		javax.swing.JPanel implements ISCGUIPanelEdit<E> {

	private BindingGroup m_bindingGroup;
	private E entity = null;

	public ASCGUIPanelEdit() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setEntity(Object entity) {

		this.entity = (E) entity;
		// Ver algo para validar nova entidade
		if (m_bindingGroup != null) {
			m_bindingGroup.unbind();
			m_bindingGroup = null;
		}
		if (entity != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	@Override
	public E getEntity() {
		return this.entity;
	}

}