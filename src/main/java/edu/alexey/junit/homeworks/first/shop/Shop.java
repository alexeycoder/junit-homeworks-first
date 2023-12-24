package edu.alexey.junit.homeworks.first.shop;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Shop {

	private List<Product> products;

	// Геттеры, сеттеры:
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	// Метод должен вернуть отсортированный по возрастанию по цене список продуктов
	public List<Product> sortProductsByPrice() {
		Objects.requireNonNull(products);

		return products.stream()
				.sorted(Comparator.comparingInt(Product::getCost))
				.toList();
	}

	// Метод должен вернуть самый дорогой продукт
	public Product getMostExpensiveProduct() {
		Objects.requireNonNull(products);

		return products.isEmpty()
				? null
				: Collections.max(products, Comparator.comparingInt(Product::getCost));
	}
}
