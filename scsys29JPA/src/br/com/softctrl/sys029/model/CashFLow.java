/**
 * 
 */
package br.com.softctrl.sys029.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Transient;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 18, 2012 7:14:43 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "cash_flow_")
public class CashFLow extends br.com.softctrl.sys029.model.base.a.ASCEntity
		implements br.com.softctrl.sys029.model.base.i.ISCEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -867575877326330200L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_code", nullable = false, length = 20)
	private String code;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_creation_user_code", referencedColumnName = "_code", nullable = false)
	private User userCreate;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "_restaurant_code", referencedColumnName = "_code", nullable = false)
	private Restaurant restaurant;

	@Column(name = "_document_number", length = 20)
	private String documentNumber;

	@Column(name = "_description", length = 65)
	private String description;

	@Column(name = "_account_date", nullable = false)
	private Timestamp accountDate;

	@Column(name = "_expiration_date", nullable = false)
	private Timestamp expirationDate;

	@Column(name = "_payment_date", nullable = false)
	private Timestamp paymentDate;

	@Column(name = "_amount", nullable = false, precision = 18, scale = 2)
	private BigDecimal amount;

	@Column(name = "_discount", nullable = false, precision = 18, scale = 2)
	private BigDecimal discount;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "_accounting_plan_code", referencedColumnName = "_code", nullable = false)
	private AccountingPlan accountingPlan;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "_cost_center_code", referencedColumnName = "_code")
	private CostCenter costCenter;

	@Column(name = "_is_canceled", nullable = false)
	private Boolean isCanceled;

	@Column(name = "_is_paid", nullable = false)
	private Boolean isPaid;

	@OneToMany(mappedBy = "cashFlow")
	private List<CouponCashMoviment> couponCashMoviments;

	/**
	 * 
	 */
	public CashFLow() {
		super();
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

	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getAccountDate() {
		return accountDate;
	}

	public void setAccountDate(Timestamp accountDate) {
		this.accountDate = accountDate;
	}

	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public AccountingPlan getAccountingPlan() {
		return accountingPlan;
	}

	public void setAccountingPlan(AccountingPlan accountingPlan) {
		this.accountingPlan = accountingPlan;
	}

	public CostCenter getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(CostCenter costCenter) {
		this.costCenter = costCenter;
	}

	public Boolean getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(Boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public List<CouponCashMoviment> getCouponCashMoviments() {
		return couponCashMoviments;
	}

	public void setCouponCashMoviments(
			List<CouponCashMoviment> couponCashMoviments) {
		this.couponCashMoviments = couponCashMoviments;
	}

}
