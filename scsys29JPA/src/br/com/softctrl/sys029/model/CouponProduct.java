package br.com.softctrl.sys029.model;

import java.math.BigDecimal;

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
 * The persistent class for the coupon_product_ database table.
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 15, 2012 2:04:16 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "coupon_product_")
public class CouponProduct extends
		br.com.softctrl.sys029.model.base.a.ASCEntity implements
		br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_discount", precision = 18, scale = 3)
	private BigDecimal discount;

	@Column(name = "_is_courtesy", nullable = false)
	private Boolean isCourtesy;

	@Column(name = "_price_tag", nullable = false, precision = 18, scale = 2)
	private BigDecimal priceTag;

	@Column(name = "_quantity", nullable = false, precision = 18, scale = 3)
	private BigDecimal quantity;

	@Column(name = "_type_discount", length = 20)
	private String typeDiscount;

	// bi-directional many-to-one association to Coupon
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_coupon_code", referencedColumnName = "_code", nullable = false)
	private Coupon coupon;

	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_product_code", referencedColumnName = "_code", nullable = false)
	private Product product;

	public CouponProduct() {
		super();
	}

	public CouponProduct(BigDecimal discount, Coupon coupon, Product product) {

		super();
		this.setDiscount(discount);
		this.setIsCourtesy(product.getIsCourtesy());
		this.setPriceTag(product.getPriceTag());
		this.setQuantity(BigDecimal.ONE);
		this.setCoupon(coupon);
		this.setProduct(product);

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
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

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getTypeDiscount() {
		return this.typeDiscount;
	}

	public void setTypeDiscount(String typeDiscount) {
		this.typeDiscount = typeDiscount;
	}

	public Coupon getCoupon() {
		return this.coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}