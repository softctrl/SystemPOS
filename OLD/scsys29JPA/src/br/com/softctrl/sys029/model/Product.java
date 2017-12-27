package br.com.softctrl.sys029.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the product_ database table.
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 10:44:23 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "product_")
public class Product extends br.com.softctrl.sys029.model.base.a.ASCEntity
		implements br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_code", nullable = false, length = 20)
	private String code;

	@Column(name = "_description", nullable = false, length = 65)
	private String description;

	// @Column(name = "_is_composition", nullable = false)
	// private Boolean isComposition;

	@Column(name = "_is_courtesy", nullable = false)
	private Boolean isCourtesy;

	@Column(name = "_price_tag", nullable = false, precision = 18, scale = 2)
	private BigDecimal priceTag;

	@Column(name = "_status", nullable = false, length = 20)
	private String status;

	// bi-directional many-to-one association to CompositionProduct
	@OneToMany(mappedBy = "product")
	private List<CompositionProduct> compositionProducts;

	// bi-directional many-to-one association to CouponProduct
	@OneToMany(mappedBy = "product")
	private List<CouponProduct> couponProducts;

	// bi-directional many-to-one association to Composition
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_composition_code", referencedColumnName = "_code", nullable = true)
	private Composition composition;

	public Product() {
		super();
	}

	public Product(String code, String description, Boolean isCourtesy,
			BigDecimal priceTag) {
		super();
		this.setCode(code);
		this.setDescription(description);
		// this.setIsComposition(isComposition);
		this.setIsCourtesy(isCourtesy);
		this.setPriceTag(priceTag);

		this.setStatus("testes");

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsComposition() {
		return (this.getComposition() != null
				&& this.getComposition().getCompositionProducts() != null && this
				.getComposition().getCompositionProducts().size() > 0);
	}

	// public void setIsComposition(Boolean isComposition) {
	// this.isComposition = isComposition;
	// }

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

	public List<CompositionProduct> getCompositionProducts() {
		return this.compositionProducts;
	}

	public void setCompositionProducts(
			List<CompositionProduct> compositionProducts) {
		this.compositionProducts = compositionProducts;
	}

	public List<CouponProduct> getCouponProducts() {
		return this.couponProducts;
	}

	public void setCouponProducts(List<CouponProduct> couponProducts) {
		this.couponProducts = couponProducts;
	}

	public Composition getComposition() {
		return this.composition;
	}

	public void setComposition(Composition composition) {
		this.composition = composition;
	}

}