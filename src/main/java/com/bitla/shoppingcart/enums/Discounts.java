package com.bitla.shoppingcart.enums;

import java.math.BigDecimal;

public class Discounts {

	private final Items itemType;
	private final int buyItem;
	private final int getItem;
	private final String discountDescription;
	private final BigDecimal price;

	public Discounts(Items apple, int buyItem, int getItem, String discountDescription, BigDecimal price) {
		this.itemType = apple;
		this.buyItem = buyItem;
		this.getItem = getItem;
		this.discountDescription = discountDescription;
		this.price = price;
	}

	public Items getItemType() {
		return itemType;
	}

	public int getBuyItem() {
		return buyItem;
	}

	public int getGetItem() {
		return getItem;
	}

	public String getDiscountDescription() {
		return discountDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	
	

}
