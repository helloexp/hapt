package com.hand.hap.sale.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hand.hap.core.annotation.MultiLanguage;
import com.hand.hap.core.annotation.MultiLanguageField;
import com.hand.hap.system.dto.BaseDTO;


@MultiLanguage
@Table(name="hap_ar_customers_b")
public class Customer extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9158921332873618917L;

	 @Id
	 @Column
	 @GeneratedValue(generator = GENERATOR_TYPE)
	 private long customerId;
	 
	 @Column
	 private String customerNumber;
	 
	 @Column
	 @MultiLanguageField
	 private String CustomerName;
	 
	 @Column
	 private long companyId;
	 
	 @Column
	 private String enabledFlag;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	
	
}
