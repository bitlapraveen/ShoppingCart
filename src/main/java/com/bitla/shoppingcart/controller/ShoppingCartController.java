package com.bitla.shoppingcart.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitla.shoppingcart.enums.Discounts;
import com.bitla.shoppingcart.enums.FinalCheckOutDetails;
import com.bitla.shoppingcart.enums.FinalCheckOutItems;
import com.bitla.shoppingcart.enums.Items;
import com.bitla.shoppingcart.enums.UserItems;

@RestController
public class ShoppingCartController {

	Map<String, Integer> userItems = new HashMap<String, Integer>();

	List<FinalCheckOutItems> finalCheckOutItems = new ArrayList<FinalCheckOutItems>();

	List<Discounts> discounts = new ArrayList<Discounts>();

	@PostConstruct
	public void init() {
		// ● 3 for the price of 2 on Oranges
		Discounts orange = new Discounts(Items.ORANGE, 2, 1, "3 for the price of 2 on Oranges", new BigDecimal(1.00));
		discounts.add(orange);

		// ● buy one, get one free on Apples
		Discounts apple = new Discounts(Items.APPLE, 1, 1, "buy one, get one free on Apples", new BigDecimal(1.00));
		discounts.add(apple);

		/*
		 * userItems.put("ORANGE", 4); userItems.put("APPLE", 5);
		 */
	}

	@GetMapping("/")
	public String getinit() {
		return "working";
	}

	@DeleteMapping("/clear")
	public boolean clear() {
		userItems.clear();
		return true;
	}

	@GetMapping("/getAllItemsWithDetails")
	public List<Discounts> getAllItemDetails() {
		return discounts;
	}

	@PostMapping("/addItems")
	public FinalCheckOutDetails addItemsCount(@RequestBody List<UserItems> userRequestItems) {
		userItems = new HashMap<String, Integer>();
		finalCheckOutItems = new ArrayList<FinalCheckOutItems>();
		for (UserItems item : userRequestItems) {
			userItems.put(item.getItemType().toUpperCase(), item.getItemCount());
		}
		if (userItems.isEmpty()) {
			return null;
		} else {
			return addDiscounts();
		}
	}

	@GetMapping(value = "/getConsolidatedBeforeCheckOutItemsDetails")
	public FinalCheckOutDetails addDiscounts() {

		Consumer<? super String> action = item -> {

			Integer integer = userItems.get(item);
			if (integer > 0) {
				FinalCheckOutItems finalItem = new FinalCheckOutItems();

				Integer itemCount = integer;

				List<Discounts> collect = discounts.stream()
						.filter(ele -> ele.getItemType().name().equalsIgnoreCase(item)).collect(Collectors.toList());

				if (collect != null && !collect.isEmpty()) {
					Discounts itemDiscount = collect.get(0);
					int discountDivisor = itemDiscount.getBuyItem() + itemDiscount.getGetItem();
					int calculateFreeItems = itemCount / discountDivisor;
					int calculatedBoughtItems = itemCount - calculateFreeItems;

					BigDecimal totalPrice = itemDiscount.getPrice().multiply(BigDecimal.valueOf(calculatedBoughtItems));

					finalItem.setCaclulatedBoughtItems(calculatedBoughtItems);
					finalItem.setCalculateFreeItems(calculateFreeItems);
					finalItem.setItemName(Items.valueOf(item).name());
					finalItem.setTotalPrice(totalPrice);
					finalItem.setTotalItems(itemCount);
					finalItem.setItemDiscountDescription(itemDiscount.getDiscountDescription());

					finalCheckOutItems.add(finalItem);
				}
			}
		};
		
		FinalCheckOutDetails finalCheckOutDetails = new FinalCheckOutDetails();

		if (userItems != null && !userItems.isEmpty()) {
			
			userItems.keySet().stream().forEach(action);
			
			finalCheckOutDetails.setCheckoutItems(finalCheckOutItems);
			
			BigDecimal price = BigDecimal.ZERO;
			for(FinalCheckOutItems ele: finalCheckOutItems) {
				price= price.add(ele.getTotalPrice());
			}
			finalCheckOutDetails.setCheckoutPrice(price);
			
			return finalCheckOutDetails;
		}
		return null;
	}

}
