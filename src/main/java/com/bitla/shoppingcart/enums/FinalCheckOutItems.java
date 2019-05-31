package com.bitla.shoppingcart.enums;

import java.math.BigDecimal;

public class FinalCheckOutItems {

	private String itemName;
	private int totalItems;
	private int caclulatedBoughtItems;
	private int calculateFreeItems;
	private BigDecimal totalPrice;
	private String itemDiscountDescription;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getCaclulatedBoughtItems() {
		return caclulatedBoughtItems;
	}

	public void setCaclulatedBoughtItems(int caclulatedBoughtItems) {
		this.caclulatedBoughtItems = caclulatedBoughtItems;
	}

	public int getCalculateFreeItems() {
		return calculateFreeItems;
	}

	public void setCalculateFreeItems(int calculateFreeItems) {
		this.calculateFreeItems = calculateFreeItems;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getItemDiscountDescription() {
		return itemDiscountDescription;
	}

	public void setItemDiscountDescription(String itemDiscountDescription) {
		this.itemDiscountDescription = itemDiscountDescription;
	}

}
