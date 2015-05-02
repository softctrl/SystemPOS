package br.com.softctrl.sys029.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the restaurant_ database table.
 * 
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 16, 2012 10:44:38 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "restaurant_")
public class Restaurant extends br.com.softctrl.sys029.model.base.a.ASCEntity
		implements br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_code", nullable = false, length = 20)
	private String code;

	@Column(name = "_creation_date", nullable = false)
	private Timestamp creationDate;

	@Column(name = "_customers_capacity", nullable = false)
	private Integer customersCapacity;

	@Column(name = "_name", nullable = false, length = 65)
	private String name;

	@Column(name = "_number_of_tables", nullable = false)
	private Integer numberOfTables;

	public Restaurant() {
		super();
	}

	public Restaurant(String code, String name, Integer customersCapacity,
			Integer numberOfTables) {
		super();
		this.setCode(code);
		this.setName(name);
		this.setCustomersCapacity(customersCapacity);
		this.setNumberOfTables(numberOfTables);

		this.setCreationDate(new Timestamp(new Date().getTime()));// TODO
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

	public Timestamp getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCustomersCapacity() {
		return this.customersCapacity;
	}

	public void setCustomersCapacity(Integer customersCapacity) {
		this.customersCapacity = customersCapacity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumberOfTables() {
		return this.numberOfTables;
	}

	public void setNumberOfTables(Integer numberOfTables) {
		this.numberOfTables = numberOfTables;
	}

}