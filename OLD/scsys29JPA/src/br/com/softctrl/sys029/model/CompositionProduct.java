package br.com.softctrl.sys029.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the composition_product_ database table.
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 10:43:58 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "composition_product_")
public class CompositionProduct extends
		br.com.softctrl.sys029.model.base.a.ASCEntity implements
		br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_is_courtesy", nullable = false)
	private Boolean isCourtesy;

	@Column(name = "_price_tag", nullable = false, precision = 18, scale = 3)
	private BigDecimal priceTag;

	@Column(name = "_status", nullable = false, length = 20)
	private String status;

	// bi-directional many-to-one association to Composition
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "_composition_code", referencedColumnName = "_code", nullable = false)
	private Composition composition;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_product_code", referencedColumnName = "_code", nullable = false)
	private Product product;

	public CompositionProduct() {
	}

	public CompositionProduct(Boolean isCourtesy, BigDecimal priceTag,
			Composition composition, Product product) {

		super();
		this.setIsCourtesy(isCourtesy);
		this.setPriceTag(priceTag == null ? product.getPriceTag() : priceTag);
		this.setComposition(composition);
		this.setProduct(product);
		this.setStatus("testes");

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsCourtesy() {
		return this.isCourtesy;
	}

	public void setIsCourtesy(Boolean isCourtesy) {
		this.isCourtesy = isCourtesy;
	}

	public BigDecimal getPriceTag() {
		return this.priceTag;
	}

	public void setPriceTag(BigDecimal priceTag) {
		this.priceTag = priceTag;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Composition getComposition() {
		return this.composition;
	}

	public void setComposition(Composition composition) {
		this.composition = composition;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}