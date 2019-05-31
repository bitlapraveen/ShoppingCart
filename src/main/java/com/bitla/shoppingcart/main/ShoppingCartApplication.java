package com.bitla.shoppingcart.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.bitla.shoppingcart.*")
@SpringBootApplication
public class ShoppingCartApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

}
