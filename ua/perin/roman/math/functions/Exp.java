package ua.perin.roman.math.functions;

/**
 * This class represents  the Euler's number e raised to the power of a double value.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Exp extends FunctionsWithOneArgument {

	public Exp(){
		this.identifier = "exp";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.exp(argument);
	}
	
}
