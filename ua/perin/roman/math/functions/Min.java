package ua.perin.roman.math.functions;

/**
 * This class represents the smaller of two double values.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Min extends FunctionsWithTwoArguments {

	public Min(){
		this.identifier = "min";
	}
	
	@Override
	public double evaluate(double firstArgument, double secondArgument) {
		return Math.min(firstArgument, secondArgument);
	}
	
}
