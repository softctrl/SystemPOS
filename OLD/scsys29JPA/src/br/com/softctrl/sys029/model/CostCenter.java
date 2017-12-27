/**
 * 
 */
package br.com.softctrl.sys029.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 18, 2012 9:20:34 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "cost_center_")
public class CostCenter extends br.com.softctrl.sys029.model.base.a.ASCEntity
		implements br.com.softctrl.sys029.model.base.i.ISCEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 414973713412688542L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_code", nullable = false, length = 3)
	private String code;

	@Column(name = "_name", nullable = false, length = 65)
	private String name;

	/**
	 * 
	 */
	public CostCenter() {

		super();

	}

	public CostCenter(String code, String name) {

		super();
		this.setCode(code);
		this.setName(name);

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
