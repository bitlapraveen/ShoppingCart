package com.bitla.shoppingcart.enums;

import java.math.BigDecimal;
import java.util.List;

public class FinalCheckOutDetails {

	private BigDecimal checkoutPrice;
	private List<FinalCheckOutItems> checkoutItems;

	public BigDecimal getCheckoutPrice() {
		return checkoutPrice;
	}

	public void setCheckoutPrice(BigDecimal checkoutPrice) {
		this.checkoutPrice = checkoutPrice;
	}

	public List<FinalCheckOutItems> getCheckoutItems() {
		return checkoutItems;
	}

	public void setCheckoutItems(List<FinalCheckOutItems> checkoutItems) {
		this.checkoutItems = checkoutItems;
	}

}
