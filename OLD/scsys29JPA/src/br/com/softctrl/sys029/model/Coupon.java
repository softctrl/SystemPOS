package br.com.softctrl.sys029.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
 * The persistent class for the coupon_ database table.
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 14, 2012 4:15:00 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "coupon_")
public class Coupon extends br.com.softctrl.sys029.model.base.a.ASCEntity
		implements br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_code", nullable = false, length = 20)
	private String code;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_restaurant_table_code", referencedColumnName = "_code", nullable = false)
	private RestaurantTable restaurantTable;

	@Column(name = "_guest_count", nullable = false)
	private Integer guestCount;

	@Column(name = "_file_number", nullable = true)
	private Integer fileNumber;

	@Column(name = "_creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "_is_canceled", nullable = false)
	private Boolean isCanceled;

	@Column(name = "_is_paid", nullable = false)
	private Boolean isPaid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_creation_user_code", referencedColumnName = "_code", nullable = false)
	private User userCreate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_canceled_user_code", referencedColumnName = "_code")
	private User userCanceled;

	@OneToMany(mappedBy = "coupon")
	private List<CouponProduct> couponProducts;

	@OneToMany(mappedBy = "coupon")
	private List<CouponCashMoviment> couponCashMoviments;

	public Coupon() {
		super();
	}

	public Coupon(RestaurantTable restaurantTable, Integer guestCount,
			Integer fileNumber, User userCreate) {

		super();
		String[] uuid = UUID.randomUUID().toString().split("-");
		this.setCode(uuid[4] + uuid[0]);
		this.setRestaurantTable(restaurantTable);
		this.setGuestCount(guestCount);
		this.setFileNumber(fileNumber);
		this.setCreationDate(new Timestamp(new Date().getTime()));
		this.setIsCanceled(false);
		this.setIsPaid(false);
		this.setUserCreate(userCreate);

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

	public RestaurantTable getRestaurantTable() {
		return restaurantTable;
	}

	public void setRestaurantTable(RestaurantTable restaurantTable) {
		this.restaurantTable = restaurantTable;
	}

	public Integer getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(Integer guestCount) {
		this.guestCount = guestCount;
	}

	public Integer getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(Integer fileNumber) {
		this.fileNumber = fileNumber;
	}

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getIsCanceled() {
		return this.isCanceled;
	}

	public void setIsCanceled(Boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public Boolean getIsPaid() {
		return this.isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public User getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

	public User getUserCanceled() {
		return this.userCanceled;
	}

	public void setUserCanceled(User userCanceled) {
		this.userCanceled = userCanceled;
	}

	public List<CouponProduct> getCouponProducts() {

		if (this.couponProducts == null) {
			this.couponProducts = new ArrayList<CouponProduct>();
		}
		return this.couponProducts;

	}

	public void setCouponProducts(List<CouponProduct> couponProducts) {
		this.couponProducts = couponProducts;
	}

	public List<CouponCashMoviment> getCouponCashMoviments() {
		return couponCashMoviments;
	}

	public void setCouponCashMoviments(
			List<CouponCashMoviment> couponCashMoviments) {
		this.couponCashMoviments = couponCashMoviments;
	}

	public CouponProduct addProduct(Product product) {

		for (CouponProduct cp : this.getCouponProducts()) {
			if (cp.getProduct().equals(product)) {
				cp.setQuantity(cp.getQuantity().add(BigDecimal.ONE));
				return cp;
			}
		}
		CouponProduct cp = new CouponProduct(BigDecimal.ZERO, this, product);
		this.getCouponProducts().add(cp);
		return cp;

	}

	public BigDecimal getDiscountTotal() {

		BigDecimal vlr = BigDecimal.ZERO;
		for (CouponProduct cp : this.getCouponProducts()) {
			vlr = vlr.add(cp.getDiscount());
		}
		return vlr;
	}

	public BigDecimal getSubTotal() {

		BigDecimal vlr = BigDecimal.ZERO;
		for (CouponProduct cp : this.getCouponProducts()) {
			vlr = vlr.add(cp.getProduct().getPriceTag()
					.multiply(cp.getQuantity()));
		}
		return vlr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder strB = new StringBuilder("");
		int tableNumber = this.getRestaurantTable().getCode();
		String creationDate = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
				.format(this.getCreationDate().getTime());

		strB.append(creationDate);
		strB.append("\nCoupon for table ");
		strB.append(tableNumber);
		for (CouponProduct cp : this.getCouponProducts()) {
			strB.append("\nProduct.: ");
			strB.append(cp.getProduct().getCode());
			strB.append("-");
			strB.append(cp.getProduct().getDescription());
			if (cp.getProduct().getIsComposition()) {
				strB.append("\n\t**Composition** ");

				for (CompositionProduct ccp : cp.getProduct().getComposition()
						.getCompositionProducts()) {
					strB.append("\n\tProduct.: ");
					strB.append(ccp.getProduct().getCode());
					strB.append("-");
					strB.append(ccp.getProduct().getDescription());
					if (ccp.getIsCourtesy()) {
						strB.append("\tR$ 0,00");
					} else {
						strB.append("\tR$ ");
						strB.append(ccp.getPriceTag());
					}
				}
			}

		}

		return strB.toString();
	}

}