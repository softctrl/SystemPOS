/**
 * 
 */
package br.com.softctrl.sys029.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import br.com.softctrl.sys029.model.enumeration.AccountType;

/**
 * @author carlostimoshenkorodrigueslopes@gmail.com
 * @date Sep 18, 2012 2:44:22 PM
 * 
 */
@Entity
@Table(schema = br.com.softctrl.sys029.model.base.u.SCDBaseUtils.DB_SCHEMA, name = "accounting_plan_")
public class AccountingPlan extends
		br.com.softctrl.sys029.model.base.a.ASCEntity implements
		br.com.softctrl.sys029.model.base.i.ISCEntity {

	private static final long serialVersionUID = -770648372413443178L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "_code", nullable = false, length = 20, unique = true)
	private String code;

	@Column(name = "_description", nullable = false, length = 100)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "_account_type", nullable = false)
	private AccountType accountType;

	@Column(name = "_is_account_root", nullable = false)
	private Boolean isAccountRoot;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "_source_account_plan_code", referencedColumnName = "_code")
	private AccountingPlan sourceAccountingPlan;

	@OneToMany(mappedBy = "sourceAccountingPlan")
	@OrderBy
	private List<AccountingPlan> accountingChildren;

	/**
	 * 
	 */
	public AccountingPlan() {
		super();
	}

	public AccountingPlan(String code, String description,
			AccountType accountType, Boolean isAccountRoot,
			AccountingPlan sourceAccountingPlan) {

		super();
		this.setCode(code);
		this.setDescription(description);
		this.setAccountType(accountType);
		this.setIsAccountRoot(isAccountRoot);
		this.setSourceAccountingPlan(sourceAccountingPlan);

	}

	public AccountingPlan addChildAccount(String code, String description) {

		return new AccountingPlan(String.format("%s%s", this.getCode(), code),
				description, this.getAccountType(), Boolean.FALSE, this);

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
		this.code = String.format("%s.", code);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Boolean getIsAccountRoot() {
		return isAccountRoot;
	}

	public void setIsAccountRoot(Boolean isAccountRoot) {
		this.isAccountRoot = isAccountRoot;
	}

	public AccountingPlan getSourceAccountingPlan() {
		return sourceAccountingPlan;
	}

	public void setSourceAccountingPlan(AccountingPlan sourceAccountingPlan) {
		this.sourceAccountingPlan = sourceAccountingPlan;
	}

	public List<AccountingPlan> getAccountingChildren() {
		return accountingChildren;
	}

	public void setAccountingChildren(List<AccountingPlan> accountingChildren) {
		this.accountingChildren = accountingChildren;
	}

	// public void print(String tab) {
	// System.out.println(tab + this.getCode() + "--" + this.getDescription());
	// for (AccountingPlan cp : getAccountingChildren()) {
	// cp.print(tab + tab);
	// }
	// }

	// public static void main(String[] args) {
	//
	// EntityDAO<AccountingPlan> dao = new EntityDAO<AccountingPlan>(
	// AccountingPlan.class);
	//
	// List<AccountingPlan> resultList = dao
	// .createQuery(
	// "from AccountingPlan a where a.isAccountRoot=:ROOT")
	// .setParameter("ROOT", Boolean.TRUE).getResultList();
	// for (AccountingPlan ap : resultList) {
	// ap.print("\t");
	// }
	//
	// }

}
