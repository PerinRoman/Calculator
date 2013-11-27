package ua.perin.roman.math.functions;

/**
 * This class represents the correctly rounded positive square root of a double value.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Sqrt extends FunctionsWithOneArgument{

	public Sqrt(){
		this.identifier = "sqrt";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.sqrt(argument);
	}	
}
