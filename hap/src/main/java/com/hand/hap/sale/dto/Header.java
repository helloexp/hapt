package com.hand.hap.sale.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.hand.hap.core.annotation.MultiLanguage;
import com.hand.hap.core.annotation.MultiLanguageField;
import com.hand.hap.system.dto.BaseDTO;

@MultiLanguage
@Table(name = "hap_om_order_headers_b")
public class Header extends BaseDTO {

	/**
	* 
	*/
	private static final long serialVersionUID = 4653899923334318384L;

	@Id
	@Column
	@GeneratedValue(generator = GENERATOR_TYPE)
	private String headerId;

	@Column
	private String orderNumber;

	@Column
	private long companyId;

	@Column
	private String orderDate;

	@Column
	@MultiLanguageField
	private String orderStatus;

	@Column
	private long customerId;

	/**
	 * 公司名
	 */
	@NotEmpty
	private String companyName;

	/**
	 * 客户名
	 */
	@NotEmpty
	private String customerName;
	/**
	 * 金额
	 */
	@NotEmpty
	private String orderSal;

	@MultiLanguageField
	private String itemDescription;

	/*
	 * LINE_ID,LINE_NUMBER,INVENTORY_ITEM_ID,
	 * ORDERD_QUANTITY,ORDER_QUANTITY_UOM,UNIT_SELLING_PRICE,DESCRIPTION
	 */
	/**
	 * 订单行ID
	 */
	@NotEmpty
	private Long lineId;

	/**
	 * 行号
	 */
	@NotEmpty
	private Long lineNumber;
	/**
	 * 产品ID
	 */
	@NotEmpty
	private Long inventoryItemId;

	/**
	 * 数量
	 */
	private long orderQuantity;
	/**
	 * 产品单位
	 */
	@NotEmpty
	private String orderQuantityUom;
	/**
	 * 销售单价
	 */
	@NotEmpty
	private long unitSellingPrice;

	/**
	 * 备注
	 */
	@NotEmpty
	private String description;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public Long getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Long getInventoryItemId() {
		return inventoryItemId;
	}

	public void setInventoryItemId(Long inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}

	public long getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(long orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getOrderQuantityUom() {
		return orderQuantityUom;
	}

	public void setOrderQuantityUom(String orderQuantityUom) {
		this.orderQuantityUom = orderQuantityUom;
	}

	public long getUnitSellingPrice() {
		return unitSellingPrice;
	}

	public void setUnitSellingPrice(long unitSellingPrice) {
		this.unitSellingPrice = unitSellingPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getHeaderId() {
		return headerId;
	}

	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}

	public String getOrderSal() {
		return orderSal;
	}

	public void setOrderSal(String orderSal) {
		this.orderSal = orderSal;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

}
