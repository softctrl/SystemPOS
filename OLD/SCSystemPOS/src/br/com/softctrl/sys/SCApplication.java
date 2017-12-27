/**
 * 
 */
package br.com.softctrl.sys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.softctrl.sys029.model.User;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 8:52:22 AM
 * 
 */
@SuppressWarnings("unused")
public class SCApplication {

	public static interface ILog {
		<E extends Exception> void warning(String tag, String message, E e);

		<E extends Exception> void error(String tag, String message, E e);

		<E extends Exception> void log(String message);
	}

	public static abstract class Log implements ILog {

	}

	public static class Log0 implements ILog {

		@Override
		public <E extends Exception> void warning(String tag, String message,
				E e) {
		}

		@Override
		public <E extends Exception> void error(String tag, String message, E e) {
		}

		@Override
		public <E extends Exception> void log(String message) {
		}

	}

	public static class Log1 implements ILog {

		@Override
		public <E extends Exception> void warning(String tag, String message,
				E e) {
			System.out.println("WARNING-" + tag + " - " + message + " - "
					+ e.getLocalizedMessage());
		}

		@Override
		public <E extends Exception> void error(String tag, String message, E e) {
			System.err.println("ERROR-" + tag + " - " + message + " - "
					+ e.getLocalizedMessage());
		}

		@Override
		public <E extends Exception> void log(String message) {
			System.out.println(message);
		}

	}

	private static SCApplication THIS;
	private ILog log;
	private User user = null;
	private Date loggedIn = null;
	private Date loggedOut = null;

	public ILog getLog() {
		return log;
	}

	private SCApplication() {
		super();
	}

	public static SCApplication getInstance() {
		if (THIS == null) {
			THIS = new SCApplication();
			// THIS.log = new Log0();//Habilita log
			THIS.log = new Log1();// Desabilita log
		}
		return THIS;
	}

	public void setLoggedIn(User user, Date date) throws ParseException {

		this.user = user;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String _date = sdf.format(date);
		this.loggedIn = sdf.parse(_date);

		getLog().log("Logou com sucesso....");
		getLog().log("C—digo Usu‡rio...: " + user.getCode());
		getLog().log("Nome Usu‡rio.....: " + user.getName());

	}

	public void setLoggedOut() {
		this.loggedOut = new Date();
	}

	public User getUser() {
		return user;
	}

	public Date getLoggedIn() {
		return loggedIn;
	}

}
