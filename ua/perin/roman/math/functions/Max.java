package ua.perin.roman.math.functions;

/**
 * This class represents the greater of two double values.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Max extends FunctionsWithTwoArguments {

	public Max(){
		this.identifier = "max";
	}
	
	@Override
	public double evaluate(double firstArgument, double secondArgument) {
		return Math.max(firstArgument, secondArgument);
	}
	
}
