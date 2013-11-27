package ua.perin.roman.math.functions;

/**
 * This class represents  the cube root.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Cbrt extends FunctionsWithOneArgument {
	
	public Cbrt(){
		this.identifier = "cbrt";
	}
	
	@Override
	public double evaluate(double argument){
		return Math.cbrt(argument);
	}
}
