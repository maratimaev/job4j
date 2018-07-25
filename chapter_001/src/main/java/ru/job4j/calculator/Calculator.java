package ru.job4j.calculator;

/**
 * Calculator.
 * @author Marat Imaev (imaevmarat@outlook.com)
 */
public class Calculator {
	private double result;

	/**
	 * Add.
	 * @param first - first.
	 * @param second - second.
	 */
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	 * Subtract.
	 * @param first - first.
	 * @param second - second.
	 */
	public void subtract(double first, double second) {
		this.result = first - second;
	}

	/**
	 * Div.
	 * @param first - first.
	 * @param second - second.
	 */
	public void div(double first, double second) {
		this.result = first / second;
	}

	/**
	 * Multiple.
	 * @param first - first.
	 * @param second - second.
	 */
	public void multiple(double first, double second) {
		this.result = first * second;
	}

	/**
	 * GetResult.
	 * @return result - result
	 */
	public double getResult() {
		return this.result;
	}

	/**
	 * Main.
	 * @param args - args.
	 *
	 */
	public static void main(String[] args) {
		System.out.println("Hello world!");
	}

}