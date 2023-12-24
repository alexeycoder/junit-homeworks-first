## Unit-тесты 1. Цели и смысл тестирования

### Задание 1.

В классе Calculator создайте метод calculateDiscount, который принимает сумму
покупки и процент скидки и возвращает сумму с учетом скидки.

Ваша задача &mdash; проверить этот метод с использованием библиотеки AssertJ.
Если метод calculateDiscount получает недопустимые аргументы, он должен
выбрасывать исключение ArithmeticException.
Не забудьте написать тесты для проверки этого поведения.

### Задание 2. (необязательное) *

Мы хотим улучшить функциональность нашего интернет-магазина.

Ваша задача &mdash; добавить два новых метода в класс Shop:

* Метод sortProductsByPrice(), который сортирует список продуктов по стоимости.
* Метод getMostExpensiveProduct(), который возвращает самый дорогой продукт.


- Напишите тесты, чтобы проверить, что магазин хранит верный список продуктов
(правильное количество продуктов, верное содержимое корзины).
- Напишите тесты для проверки корректности работы метода getMostExpensiveProduct.
- Напишите тесты для проверки корректности работы метода sortProductsByPrice
(проверьте правильность сортировки).

Используйте класс Product для создания экземпляров продуктов и класс Shop для
написания методов сортировки и тестов.

## Решение

### Calculator

Метод calculateDiscount &mdash; [class Calculator](src/main/java/edu/alexey/junit/homeworks/first/calculator/Calculator.java)

Тесты метода &mdash; [class CalculatorTest](src/main/java/edu/alexey/junit/homeworks/first/calculator/CalculatorTest.java)

*Пример тестирования*:

	mvn clean compile
	mvn exec:java

![CalculatorTest](https://github.com/alexeycoder/illustrations/blob/main/java-junit-hw1/calculator-test.png?raw=true)

### Shop

	mvn clean compile
	mvn exec:java -DmainClass=edu.alexey.junit.homeworks.first.shop.ShopTest

Метод calculateDiscount &mdash; [class Shop](src/main/java/edu/alexey/junit/homeworks/first/shop/Shop.java)

Тесты метода &mdash; [class ShopTest](src/main/java/edu/alexey/junit/homeworks/first/shop/ShopTest.java)

*Пример тестирования*:

![ShopTest](https://github.com/alexeycoder/illustrations/blob/main/java-junit-hw1/shop-test.png?raw=true)






