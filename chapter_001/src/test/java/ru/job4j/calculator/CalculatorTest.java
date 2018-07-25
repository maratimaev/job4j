package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test calculator.
 *
 * @author Marat Imaev (imaevmarat@outlook.com)
 */
public class CalculatorTest {
	@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1, 1);
		double result = calc.getResult();
		double expected = 2;
		assertThat(result, is(expected));
	}

	@Test
	public void whenSubtractOneMinusOneThenZero() {
		Calculator calc = new Calculator();
		calc.subtract(1, 1);
		double result = calc.getResult();
		double expected = 0;
		assertThat(result, is(expected));
	}

	@Test
	public void whenDivFourOnTwoThenTwo() {
		Calculator calc = new Calculator();
		calc.div(4, 2);
		double result = calc.getResult();
		double expected = 2;
		assertThat(result, is(expected));
	}

	@Test
	public void whenMultipleTwoOnTwoThenFour() {
		Calculator calc = new Calculator();
		calc.multiple(2, 2);
		double result = calc.getResult();
		double expected = 4;
		assertThat(result, is(expected));
	}
}
