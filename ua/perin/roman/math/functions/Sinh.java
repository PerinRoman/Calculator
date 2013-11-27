package ua.perin.roman.math.functions;

/**
 * This class represents the hyperbolic sine of a double value.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Sinh extends FunctionsWithOneArgument {

	public Sinh(){
		this.identifier = "sinh";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.sinh(argument);
	}
	
}
