package ua.perin.roman.math.functions;

/**
 * This class represents  the arc tangent.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class ATan extends FunctionsWithOneArgument {

	public ATan(){
		this.identifier = "atan";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.atan(argument);
	}
}
