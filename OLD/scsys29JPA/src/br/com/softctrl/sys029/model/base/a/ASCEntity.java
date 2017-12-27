/**
 * 
 */
package br.com.softctrl.sys029.model.base.a;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 13, 2012 7:44:24 PM
 * 
 */
public abstract class ASCEntity {

	@javax.persistence.Transient
	private java.beans.PropertyChangeSupport change = new java.beans.PropertyChangeSupport(
			this);

	public ASCEntity() {
	}

	public void addPropertyChangeListener(
			java.beans.PropertyChangeListener listener) {
		change.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(
			java.beans.PropertyChangeListener listener) {
		change.removePropertyChangeListener(listener);
	}

}
