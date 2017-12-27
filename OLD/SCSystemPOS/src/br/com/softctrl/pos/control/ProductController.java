/**
 * 
 */
package br.com.softctrl.pos.control;

import java.util.List;

import br.com.softctrl.sys029.model.Product;
import br.com.softctrl.sys029.model.dao.EntityDAO;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 9:53:06 PM
 * 
 */
public class ProductController {

	EntityDAO<Product> productDAO = new EntityDAO<Product>(Product.class);

	/**
	 * 
	 */
	public ProductController() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		return productDAO.createQuery("from Product p").getResultList();
	}

}
