package ua.perin.roman.math.functions;

/**
 * This class represents  the trigonometric tangent of an angle.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Tan extends FunctionsWithOneArgument {

	public Tan(){
		this.identifier = "tan";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.tan(argument);
	}
}
