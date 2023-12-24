package edu.alexey.junit.homeworks.first;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCollection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		// Проверьте, как работают методы:

		assertConditionA();

		assertConditionB();

		System.out.println(sum(2_147_483_647, 1));

		try {
			happyNY();
		} catch (AssertionError e) {
			System.err.println(e.getMessage());
		}

		expectedValue();

		checkingShoppingCart();

		String[] colors = { "aqua", "yellow", "blue", "orange", "green", "violet", "gold" };
		assertThat(colors)
				.isNotEmpty() // Массив не должен быть пустым
				.hasSize(7) // Размер массива должен быть равен 7
				.doesNotHaveDuplicates() // Массив не должен содержать повторяющихся элементов
				.contains("orange", "green", "violet") // Массив должен содержать цвета: "orange", "green", "violet"
				.endsWith("gold") //  Последним цветом в массиве должен быть "gold"
				.startsWith("aqua") // Первым цветом в массиве должен быть "aqua"
				.containsSequence("yellow", "blue") // В массиве должна быть последовательность цветов "yellow", "blue"
				.doesNotContain("red", "black"); //Массив не должен содержать цвета: "red", "black"

		testingJavaCollectionsAssertJ(colors);

		checkingHero();
	}

	//                  Практические задания:
	// 1.1
	public static void assertConditionA() {
		String[] weekends = { "Суббота", "Воскресенье" };
		assert weekends.length == 2;
		System.out.println("В неделе " + weekends.length + " дня выходных");
	}

	// 1.2
	public static void assertConditionB() {
		int x = 1;
		assert x >= 0;
	}

	// 1.3
	// assert boolean_выражение : сообщение_об_ошибке;
	// Ariane V - https://habr.com/ru/company/pvs-studio/blog/306748/
	// sum(2_147_483_647, 1) возвращает "-2147483648"
	public static int sum(int a, int b) {
		int r = a + b;
		// HD 2-12 Overflow iff both arguments have the opposite sign of the result
		if (((a ^ r) & (b ^ r)) < 0) {
			System.err.println("Переполнение целочисленного типа. Результат некорректный!");
		}
		return r;
	}

	// 1.4
	// 08/12/2022 06:19:41
	// windows fail - https://habr.com/ru/company/pvs-studio/blog/698404/
	public static void happyNY() {

		var currentDateTime = LocalDateTime.now();
		if (currentDateTime.isBefore(LocalDateTime.of(2024, 1, 1, 0, 0))) {
			throw new AssertionError("Еще 2023 год :(");
		} else if (currentDateTime.isBefore(LocalDateTime.of(2024, 1, 1, 1, 0))) {
			System.out.println("С новым годом!");
		}
	}

	// 1.5
	// выражение assert верно, нужно исправить код
	// Вот наиболее распространенный вариант использования. Предположим, вы включаете значение enum:
	public static void checkingShoppingCart() {
		ArrayList<String> productCategories = new ArrayList<>();
		productCategories.add("fruits");
		productCategories.add("vegetables");
		productCategories.add("bakery");
		productCategories.add("beverages");

		ArrayList<String> products = new ArrayList<>();
		products.add("apple");
		products.add("tomato");
		products.add("bread");
		products.add("water");

		for (String product : products) {
			if (product.equals("apple")) {
				System.out.println("category: " + productCategories.get(0));
			} else if (product.equals("tomato")) {
				System.out.println("category: " + productCategories.get(1));
			} else if (product.equals("bread")) {
				System.out.println("category: " + productCategories.get(2));
			} else if (product.equals("water")) {
				System.out.println("category: " + productCategories.get(3));
			} else {
				assert false : "Unknown category for the product " + product;
			}
		}
	}

	// 1.6
	//   AssertJ
	// ok - assertThat(actual/фактическое).isEqualTo(expected/ожидаемое);
	// not ok - assertThat(expected/ожидаемое).isEqualTo(actual/фактическое);
	public static void expectedValue() {
		assertThat(sum(2, 3)).isEqualTo(5);
	}

	// 1.7
	public static void testingJavaCollectionsAssertJ(String[] colors) {
		assertThat(colors)
				.isNotEmpty() // Массив не должен быть пустым
				.hasSize(7) // Размер массива должен быть равен 7
				.doesNotHaveDuplicates() // Массив не должен содержать повторяющихся элементов
				.contains("orange", "green", "violet") // Массив должен содержать цвета: "orange", "green", "violet"
				.endsWith("gold") //  Последним цветом в массиве должен быть "gold"
				.startsWith("aqua") // Первым цветом в массиве должен быть "aqua"
				.containsSequence("yellow", "blue") // В массиве должна быть последовательность цветов "yellow", "blue"
				.doesNotContain("red", "black"); //Массив не должен содержать цвета: "red", "black"
	}

	// 1.8
	public static void checkingHero() {

		List<String> heroBag = Arrays.asList("Bow", "Axe", "Gold");
		Hero emmett = new Hero("Emmett", 50, "sword", heroBag, true);

		/*
		1. Проверить, что герой создался с именем Emmett
		2. Проверить, что значение прочности брони героя равно 50
		3. Проверить, что у героя оружие типа sword
		4. Проверить содержимое инвентаря героя (не пустой, размер 3, содержимое "Bow", "Axe", "Gold", порядок не важен)
		5. Проверить, что герой человек (свойство true)
		*/
		assert "Emmett".equals(emmett.getName()) : "Name should be Emmett";
		assertThat(emmett.armorStrength).isEqualTo(50);
		assertThat(emmett.weapon).isEqualTo("sword");
		assertThatCollection(emmett.getBag())
				.isNotEmpty()
				.hasSize(3)
				.contains("Bow", "Axe", "Gold");
		assert emmett.isHuman() : "Герой не является человеком.";
	}

	// 1.8 (Черный ящик)
	static class Hero {
		private String name;
		private int armorStrength;
		private String weapon;
		private List<String> bag;
		private boolean isHuman;

		public Hero(String name, int armorStrength, String weapon, List<String> bag, boolean isHuman) {
			this.name = name;
			this.armorStrength = armorStrength;
			this.weapon = weapon;
			this.bag = bag;
			this.isHuman = isHuman;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getArmorStrength() {
			return armorStrength;
		}

		public void setArmorStrength(int armorStrength) {
			this.armorStrength = armorStrength;
		}

		public String getWeapon() {
			return weapon;
		}

		public void setWeapon(String weapon) {
			this.weapon = weapon;
		}

		public List<String> getBag() {
			return bag;
		}

		public void setBag(List<String> bag) {
			this.bag = bag;
		}

		public boolean isHuman() {
			return isHuman;
		}

		public void setHuman(boolean human) {
			isHuman = human;
		}
	}
}