package ua.perin.roman.math.functions;

/**
 * This class must be extended by all classes that 
 * represent function with two argument.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public abstract class FunctionsWithTwoArguments {

	String identifier;
	
	public String getIdentifier(){
		return this.identifier;
	}
	
	public abstract double evaluate(double firstArgument, double secondArgument);
	
}
