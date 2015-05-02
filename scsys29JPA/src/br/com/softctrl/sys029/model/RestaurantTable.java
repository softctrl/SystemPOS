/**
 * 
 */
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
 * @date Sep 16, 2012 10:43:24 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "restaurant_table_")
public class RestaurantTable extends
		br.com.softctrl.sys029.model.base.a.ASCEntity implements
		br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = -8706884435720547034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "_code", nullable = false, unique = true)
	private Integer code;

	@Column(name = "_description", nullable = true)
	private String description;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "_restaurant_code", referencedColumnName = "_code", nullable = false)
	private Restaurant restaurant;

	@OneToMany(mappedBy = "restaurantTable")
	private List<Coupon> coupons;

	public RestaurantTable() {
		super();

	}

	public RestaurantTable(Restaurant restaurant, Integer code,
			String description) {

		super();
		this.setCode(code);
		this.setDescription(description);
		this.setRestaurant(restaurant);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void isOccupied() {
	}

}
