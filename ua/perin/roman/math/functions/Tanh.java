package ua.perin.roman.math.functions;

/**
 * This class represents the hyperbolic tangent of a double value.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Tanh extends FunctionsWithOneArgument {

	public Tanh(){
		this.identifier = "tanh";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.tanh(argument);
	}
}
