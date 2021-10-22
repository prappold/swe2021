package at.edu.c02.calculator.logic;

import java.util.Stack;

import at.edu.c02.calculator.Calculator;
import at.edu.c02.calculator.CalculatorException;


public class CalculatorImpl implements Calculator {

	private Stack<Double> stack_ = new Stack<Double>();

	@Override
	public double perform(Operation op) throws CalculatorException {



		double b = pop();
		double a = pop();

		switch (op) {
		case add:
			return a + b;
		case sub:
			return a - b;
		case div:
			double c = a / b;
			if (Double.isInfinite(c))
				throw new CalculatorException("Division by zero");
			return c;
		case mul:
			return a * b;

		case mod:
			if (a < 0) {
				return floatModfirst(a, b);
			} else if (b < 0) {
				return floatModsecond(a, b);
			}
			else return floatModfirst(a,b);

		}
		return 0;
	}

	@Override
	public double pop() throws CalculatorException {
		if (stack_.isEmpty())
			throw new CalculatorException();
		return stack_.pop();
	}

	@Override
	public void push(double v) {
		stack_.push(v);
	}

	@Override
	public void clear() {
		stack_.clear();
	}

	double floatModfirst(double x, double y){
		// x mod y behaving the same way as Math.floorMod but with doubles
		return (x - Math.floor(x/y) * y);
	}

	double floatModsecond(double y, double x){
		// x mod y behaving the same way as Math.floorMod but with doubles
		return (y - Math.floor(y/x) * x);
	}

}
