package br.com.softctrl.sys029.model;

import java.util.List;

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
 * The persistent class for the user_ database table.
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 10:44:49 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "user_")
public class User extends br.com.softctrl.sys029.model.base.a.ASCEntity
		implements br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = 6654991915111734488L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_code", nullable = false, length = 20)
	private String code;

	@Column(name = "_is_active", nullable = false)
	private Boolean isActive;

	@Column(name = "_name", nullable = false, length = 30)
	private String name;

	@Column(name = "_password", nullable = false, length = 30)
	private String password;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "_restaurant_code", referencedColumnName = "_code", nullable = false)
	private Restaurant restaurant;

	@OneToMany(mappedBy = "userCreate")
	private List<Coupon> couponsCreated;

	@OneToMany(mappedBy = "userCreate")
	private List<CashFLow> cashFlowCreated;

	@OneToMany(mappedBy = "userCanceled")
	private List<Coupon> couponsCanceled;

	public User() {
		super();
	}

	public User(Restaurant restaurant, String code, String name, String password) {
		super();
		this.setCode(code);
		this.setName(name);
		this.setPassword(password);
		this.setIsActive(true);
		this.setRestaurant(restaurant);
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

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Coupon> getCouponsCreated() {
		return this.couponsCreated;
	}

	public void setCouponsCreated(List<Coupon> couponsCreated) {
		this.couponsCreated = couponsCreated;
	}

	public List<CashFLow> getCashFlowCreated() {
		return cashFlowCreated;
	}

	public void setCashFlowCreated(List<CashFLow> cashFlowCreated) {
		this.cashFlowCreated = cashFlowCreated;
	}

	public List<Coupon> getCouponsCanceled() {
		return this.couponsCanceled;
	}

	public void setCouponsCanceled(List<Coupon> couponsCanceled) {
		this.couponsCanceled = couponsCanceled;
	}

}