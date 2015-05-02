/**
 * 
 */
package br.com.softctrl.sys029.model.enumeration;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 18, 2012 2:53:32 PM
 * 
 */
public enum AccountType {

	DEBIT, CREDIT;

	@Override
	public String toString() {
		if (this.equals(DEBIT)) {
			return "D�bito";
		} else if (this.equals(CREDIT)) {
			return "Cr�dito";
		} else {
			return super.toString();
		}
	};
}
