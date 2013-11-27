package ua.perin.roman.math.functions;

/**
 * This class represents  the trigonometric cosine of an angle.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Cos extends FunctionsWithOneArgument {
		
	public Cos(){
		this.identifier = "cos";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.cos(argument);
	}

}
