/**
 * 
 */
package br.com.softctrl.pos.control.login;

import javax.persistence.Query;

import br.com.softctrl.sys029.model.User;
import br.com.softctrl.sys029.model.dao.EntityDAO;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 9:26:12 AM
 * 
 */
public class LoginControl {

	private static EntityDAO<User> userDAO = new EntityDAO<User>(User.class);

	/**
	 * 
	 */
	public LoginControl() {
		super();
	}

	@SuppressWarnings("unchecked")
	public User login(String code, String password) {
		Query qry = userDAO
				.createQuery(
						"from User u where u.code=:code and u.password=:passwd and u.isActive=:act")
				.setParameter("code", code).setParameter("passwd", password)
				.setParameter("act", true);
		java.util.List<User> users = qry.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);
		} else {
			return null;
		}
	}
}
