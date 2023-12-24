package edu.alexey.junit.homeworks.first.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

public class CalculatorTest {

	public static void main(String[] args) {

		System.out.println("\nТесты метода рассчёта суммы с учётом скидки:\n".toUpperCase());
		discountedAmountCalcForValidInputsIsCorrect();
		discountedAmountCalcForValidEdgeInputsIsCorrect();
		discountedAmountCalcForMaxDiscountDoesNotCauseException();
		discountedAmountCalcForNegativeDiscountCausesException();
		discountedAmountCalcForOvermaxDiscountCausesException();
		discountedAmountCalcForNegativeAmtCausesException();
		discountedAmountCalcForNanAmtCausesArithmeticException();

		// System.out.println("\nБазовые тесты (семинар №1):\n".toUpperCase());
		// basicTestsSeminar1();
	}

	static void discountedAmountCalcForValidInputsIsCorrect() {

		assertThat(Calculator.calculatingDiscount(1000, 25)).isNotNegative().isEqualTo(1000 - 250);
		final double amt = 456789.12;
		assertThat(Calculator.calculatingDiscount(amt, 15)).isNotNegative()
				.isCloseTo(amt * (1d - 15d / 100), within(1e-3));

		printOk();
	}

	static void discountedAmountCalcForValidEdgeInputsIsCorrect() {

		final double amt = 12345.99;
		assertThat(Calculator.calculatingDiscount(amt, 0)).isEqualTo(amt);
		// Проверяем равенство итога 0 для скидки 100%, только если 100% является
		// допустимым значением скидки в принципе:
		if (Calculator.MAX_DISCOUNT_PCT >= 100) {
			assertThat(Calculator.calculatingDiscount(amt, 100)).isEqualTo(0);
		}

		printOk();
	}

	static void discountedAmountCalcForMaxDiscountDoesNotCauseException() {

		assertThatCode(() -> Calculator.calculatingDiscount(12345, Calculator.MAX_DISCOUNT_PCT))
				.doesNotThrowAnyException();

		printOk();
	}

	static void discountedAmountCalcForNegativeDiscountCausesException() {

		assertThatThrownBy(() -> Calculator.calculatingDiscount(123, -1))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Недопустимое значение процента скидки");

		printOk();
	}

	static void discountedAmountCalcForOvermaxDiscountCausesException() {

		assertThatThrownBy(() -> Calculator.calculatingDiscount(123, Calculator.MAX_DISCOUNT_PCT + 1))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Недопустимое значение процента скидки");

		printOk();
	}

	static void discountedAmountCalcForNegativeAmtCausesException() {
		final String exMsg = "Сумма покупки не может быть отрицательной";
		assertThatThrownBy(() -> Calculator.calculatingDiscount(-0.01, 25))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(exMsg);
		assertThatThrownBy(() -> Calculator.calculatingDiscount(Double.NEGATIVE_INFINITY, 0))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining(exMsg);

		printOk();
	}

	static void discountedAmountCalcForNanAmtCausesArithmeticException() {

		assertThatThrownBy(() -> Calculator.calculatingDiscount(Double.NaN, 0))
				.isInstanceOf(ArithmeticException.class)
				.hasMessage("not a number");
		assertThatThrownBy(() -> Calculator.calculatingDiscount(Double.POSITIVE_INFINITY, 0))
				.isInstanceOf(ArithmeticException.class)
				.hasMessage("infinite");

		printOk();
	}

	static void basicTestsSeminar1() {

		// Проверка базового функционала с целыми числами:
		if (8 != Calculator.calculation(2, 6, '+')) {
			throw new AssertionError("Ошибка в методе");
		}

		if (0 != Calculator.calculation(2, 2, '-')) {
			throw new AssertionError("Ошибка в методе");
		}

		if (14 != Calculator.calculation(2, 7, '*')) {
			throw new AssertionError("Ошибка в методе");
		}

		if (2 != Calculator.calculation(100, 50, '/')) {
			throw new AssertionError("Ошибка в методе");
		}

		// Случаи с неправильными аргументами
		// аргумент operator типа char, должен вызывать исключение, если он получает не
		// базовые символы (+-*/)
		try {
			Calculator.calculation(8, 4, '_');
		} catch (IllegalArgumentException e) {
			if (!e.getMessage().equals("Unexpected value operator: _")) {
				throw new AssertionError("Ошибка в методе");
			}
		}

		// Проверка базового функционала с целыми числами, с использованием утверждений:
		assert 8 == Calculator.calculation(2, 6, '+');
		assert 0 == Calculator.calculation(2, 2, '-');
		assert 14 == Calculator.calculation(2, 7, '*');
		assert 2 == Calculator.calculation(100, 50, '/');

		// Проверка базового функционала с целыми числами, с использованием утверждений
		// AssertJ:
		assertThat(Calculator.calculation(2, 6, '+')).isEqualTo(8);
		assertThat(Calculator.calculation(2, 2, '-')).isEqualTo(0);
		assertThat(Calculator.calculation(2, 7, '*')).isEqualTo(14);
		assertThat(Calculator.calculation(100, 50, '/')).isEqualTo(2);

		// Проверка ожидаемого исключения, с использованием утверждений AssertJ:
		assertThatThrownBy(() -> Calculator.calculation(8, 4, '_')).isInstanceOf(IllegalArgumentException.class);

		assertThatThrownBy(() -> Calculator.calculation(2_147_483_647, 1, '+')).isInstanceOf(ArithmeticException.class)
				.hasMessage("integer overflow");
		System.out.println(Calculator.squareRootExtraction(169));

		// Примерные решения домашних заданий из 1 лекции:

		// HW1.1: Придумайте и опишите (можно в псевдокоде) функцию извлечения корня и
		// необходимые проверки для него используя граничные случаи
		assertThat(Calculator.squareRootExtraction(0)).isEqualTo(0);
		assertThat(Calculator.squareRootExtraction(0.0)).isEqualTo(0.0);
		assertThat(Calculator.squareRootExtraction(-0.0)).isEqualTo(-0.0);
		assertThat(Calculator.squareRootExtraction(1)).isEqualTo(1);
		assertThat(Calculator.squareRootExtraction(Double.POSITIVE_INFINITY)).isEqualTo(Double.POSITIVE_INFINITY);
		assertThatThrownBy(() -> Calculator.squareRootExtraction(-1)).isInstanceOf(IllegalArgumentException.class);

		// HW1.2: Как будет выглядеть проверка для случая деления на ноль? (с
		// использованием AssertJ)
		assertThatThrownBy(() -> Calculator.calculation(5, 0, '/')).isInstanceOf(ArithmeticException.class);

		// HW1.3: Сравните одну и ту же проверку с использованием условий, ассертов,
		// AssertJ
		// в каком случае стандартное сообщение об ошибке будет более информативным?

		try {
			if (0 != Calculator.calculation(2, 6, '+')) {
				throw new AssertionError("Ошибка в методе");
			}
		} catch (AssertionError e) {
			e.printStackTrace();
		}
		try {
			assert 0 == Calculator.calculation(2, 6, '+');
		} catch (AssertionError e) {
			e.printStackTrace();
		}
		try {
			assertThat(Calculator.calculation(2, 6, '+')).isEqualTo(0);
		} catch (AssertionError e) {
			e.printStackTrace();
		}

	}

	private static void printOk() {
		String callerName = StackWalker.getInstance()
				.walk(s -> s.skip(1).findFirst())
				.get()
				.getMethodName();
		System.out.println(callerName + ": OK");
	}
}