package br.com.softctrl.sys029.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the composition_ database table.
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 10:43:48 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "composition_")
public class Composition extends br.com.softctrl.sys029.model.base.a.ASCEntity
		implements br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_code", nullable = false, length = 20)
	private String code;

	// bi-directional many-to-one association to CompositionProduct
	@OneToMany(mappedBy = "composition", cascade = { CascadeType.ALL })
	private List<CompositionProduct> compositionProducts;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "composition")
	private List<Product> products;

	public Composition() {
		super();
	}

	public Composition(String code) {
		super();
		this.setCode(code);
	}

	//
	public CompositionProduct addProduct(Boolean isCourtesy,
			BigDecimal priceTag, Product product) {

		CompositionProduct comp = new CompositionProduct(isCourtesy, priceTag,
				this, product);
		this.getCompositionProducts().add(comp);
		return comp;

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CompositionProduct> getCompositionProducts() {
		if (this.compositionProducts == null) {
			this.compositionProducts = new ArrayList<CompositionProduct>();
		}
		return this.compositionProducts;
	}

	public void setCompositionProducts(
			List<CompositionProduct> compositionProducts) {
		this.compositionProducts = compositionProducts;
	}

	public List<Product> getProducts() {
		if (this.products == null) {
			this.products = new ArrayList<Product>();
		}

		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}