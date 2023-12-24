package edu.alexey.junit.homeworks.first.shop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCollection;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class ShopTest {

	static final List<Product> PRODUCTS = generateProducts();

	static List<Product> generateProducts() {
		// most expensive = beef, 250
		final String[] titles = new String[] {
				"bacon", "beef", "ham", "salmon", "carrot", "potato",
				"onion", "apple", "melon", "rice", "eggs", "yogurt"
		};
		final int[] costs = new int[] {
				170, 250, 200, 150, 15, 30, 20, 59, 88, 100, 80, 55
		};
		return IntStream.range(0, titles.length).mapToObj(i -> {
			var p = new Product();
			p.setTitle(titles[i]);
			p.setCost(costs[i]);
			return p;
		}).toList();
	}

	/*
	1. Напишите тесты, чтобы проверить, что магазин хранит верный список продуктов (правильное количество продуктов, верное содержимое корзины).
	2. Напишите тесты для проверки корректности работы метода getMostExpensiveProduct.
	3. Напишите тесты для проверки корректности работы метода sortProductsByPrice (проверьте правильность сортировки).
	*/
	public static void main(String[] args) {
		System.out.println("\nТесты методов экземпляра SHOP:\n".toUpperCase());

		shopOnlyContainsGivenProducts();

		sortProductsByPriceCausesNpeOnUnitializedShop();
		sortProductsByPriceReturnsEmptyListIfNoProducts();
		sortProductsByPriceReturnsSortedList();

		getMostExpensiveProductCausesNpeOnUnitializedShop();
		getMostExpensiveProductReturnsNullIfNoProducts();
		getMostExpensiveProductResultIsCorrect();
	}

	static void shopOnlyContainsGivenProducts() {

		Shop shop = new Shop();
		shop.setProducts(List.of());

		assertThatCollection(shop.getProducts()).isEmpty();

		shop.setProducts(PRODUCTS);

		assertThatCollection(shop.getProducts())
				.hasSameSizeAs(PRODUCTS)
				.extracting(Product::getTitle)
				.containsExactlyInAnyOrder(PRODUCTS.stream().map(Product::getTitle).toArray(String[]::new));
		assertThatCollection(shop.getProducts())
				.extracting(Product::getCost)
				.containsExactlyInAnyOrder(PRODUCTS.stream().map(Product::getCost).toArray(Integer[]::new));

		//	PRODUCTS.forEach(prod -> {
		//	assert shop.getProducts().stream()
		//		.anyMatch(p -> p.getTitle().equals(prod.getTitle()) && p.getCost() == prod.getCost())
		//		: String.format("Не найден продукт '%s %d'", prod.getTitle(), prod.getCost());
		//	});
		printOk();
	}

	static void sortProductsByPriceCausesNpeOnUnitializedShop() {
		Shop shop = new Shop();

		assertThatNullPointerException().isThrownBy(() -> shop.sortProductsByPrice());
		printOk();
	}

	static void sortProductsByPriceReturnsEmptyListIfNoProducts() {
		Shop shop = new Shop();
		shop.setProducts(List.of());

		assertThatCollection(shop.sortProductsByPrice()).isEmpty();
		printOk();
	}

	static void sortProductsByPriceReturnsSortedList() {
		Shop shop = new Shop();
		shop.setProducts(PRODUCTS);

		assertThat(shop.sortProductsByPrice()).isSortedAccordingTo(Comparator.comparingInt(Product::getCost));
		printOk();
	}

	static void getMostExpensiveProductCausesNpeOnUnitializedShop() {
		Shop shop = new Shop();
		assertThatNullPointerException().isThrownBy(() -> shop.getMostExpensiveProduct());
		printOk();
	}

	private static void getMostExpensiveProductReturnsNullIfNoProducts() {
		Shop shop = new Shop();
		shop.setProducts(List.of());
		assertThat(shop.getMostExpensiveProduct()).isNull();
		printOk();
	}

	private static void getMostExpensiveProductResultIsCorrect() {
		Shop shop = new Shop();
		shop.setProducts(PRODUCTS);
		assertThat(shop.getMostExpensiveProduct()).extracting(Product::getCost).isEqualTo(250);
		printOk();
	}

	private static void printOk() {
		String callerName = StackWalker.getInstance()
				.walk(s -> s.skip(1).findFirst())
				.get()
				.getMethodName();
		System.out.println(callerName + ": OK");
	}
}
