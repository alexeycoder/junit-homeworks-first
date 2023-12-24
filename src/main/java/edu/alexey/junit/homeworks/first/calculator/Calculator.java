package edu.alexey.junit.homeworks.first.calculator;

public class Calculator {

	public static int calculation(int firstOperand, int secondOperand, char operator) {
		int result;

		switch (operator) {
		case '+':
			result = Math.addExact(firstOperand, secondOperand);
			break;
		case '-':
			result = Math.subtractExact(firstOperand, secondOperand);
			break;
		case '*':
			result = Math.multiplyExact(firstOperand, secondOperand);
			break;
		case '/':
			result = Math.divideExact(firstOperand, secondOperand);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value operator: " + operator);
		}
		return result;
	}

	// HW1.1: Придумайте и опишите (можно в псевдокоде) функцию извлечения корня и
	// необходимые проверки для него используя граничные случаи
	public static double squareRootExtraction(double num) {
		// 0
		// Отрицательные числа
		// Дробные значения корней
		// Целые
		if (num < 0) {
			throw new IllegalArgumentException("Cannot calculate square root of a negative number");
		}
		return Math.sqrt(num);
	}

	public static final int MAX_DISCOUNT_PCT = 100;

	/**
	 * @param purchaseAmount  Сумма покупки.
	 * @param discountPercent Размер скидки в процентах от суммы покупки.
	 * @return Сумма с учётом скидки.
	 */
	public static double calculatingDiscount(double purchaseAmount, int discountPercent) {

		// validate purchaseAmount
		if (Double.isNaN(purchaseAmount)) {
			throw new ArithmeticException("not a number");
		}
		if (purchaseAmount < 0) {
			throw new IllegalArgumentException("Сумма покупки не может быть отрицательной: " + purchaseAmount);
		}
		if (Double.isInfinite(purchaseAmount)) {
			throw new ArithmeticException("infinite");
		}

		// validate discountPercent
		if (discountPercent < 0 || discountPercent > MAX_DISCOUNT_PCT) {
			throw new IllegalArgumentException(
					"Недопустимое значение процента скидки: " + discountPercent
							+ System.lineSeparator()
							+ "Значение должно быть в интервале от 0 до " + MAX_DISCOUNT_PCT);
		}
		if (0 == discountPercent) {
			return purchaseAmount;
		} else if (100 == discountPercent) {
			return 0;
		}

		double discountAmount = purchaseAmount * discountPercent / 100d;
		return purchaseAmount - discountAmount;
	}
}