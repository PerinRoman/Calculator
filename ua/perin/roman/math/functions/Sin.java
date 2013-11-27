package ua.perin.roman.math.functions;

/**
 * This class represents  the trigonometric sine of an angle.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Sin extends FunctionsWithOneArgument{
	
	public Sin(){
		this.identifier = "sin";
	}
	
	@Override
	public double evaluate(double argument){
		return Math.sin(argument);
	}
}
