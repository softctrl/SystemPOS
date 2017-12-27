/**
 * 
 */
package br.com.softctrl.util.gui;

import java.sql.Timestamp;
import java.util.Date;

import org.jdesktop.beansbinding.Converter;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 20, 2012 1:19:31 PM
 * 
 */
public class SCTimestampDateConverter extends Converter<Timestamp, Date> {

	@Override
	public Date convertForward(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	@Override
	public Timestamp convertReverse(Date date) {
		return new Timestamp(date.getTime());
	}

}
