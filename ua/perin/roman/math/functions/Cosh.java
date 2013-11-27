package ua.perin.roman.math.functions;

/**
 * This class represents  the hyperbolic cosine.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Cosh extends FunctionsWithOneArgument {

	public Cosh(){
		this.identifier = "cosh";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.cosh(argument);
	}
	
}
