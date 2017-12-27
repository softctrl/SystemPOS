/**
 * 
 */
package br.com.softctrl.pos.gui.financial;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 19, 2012 8:46:47 PM
 * 
 */
public final class SCAutoBindingUtils<S1, T1, S2, T2> {

	private String name1;
	private String name2;
	private S1 entity1;
	private S2 entity2;

	public SCAutoBindingUtils(S1 entity1, String name1, S2 entity2, String name2) {
		super();
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.name1 = name1;
		this.name2 = name2;
	}

	BeanProperty<S1, T1> gen1() {
		return BeanProperty.create(this.name1);
	}

	BeanProperty<S2, T2> gen2() {
		return BeanProperty.create(this.name2);
	}

	AutoBinding<S1, T1, S2, T2> binding() {

		AutoBinding<S1, T1, S2, T2> _auto_binding = Bindings.createAutoBinding(
				AutoBinding.UpdateStrategy.READ_WRITE, this.entity1,
				this.gen1(), this.entity2, this.gen2());
		_auto_binding.bind();
		return _auto_binding;

	}

	AutoBinding<S1, T1, S2, T2> binding(AutoBinding.UpdateStrategy strategy) {

		AutoBinding<S1, T1, S2, T2> _auto_binding = Bindings.createAutoBinding(
				strategy, this.entity1, this.gen1(), this.entity2, this.gen2());
		_auto_binding.bind();
		return _auto_binding;

	}

}
