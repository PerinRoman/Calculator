package ua.perin.roman.math.functions;

/**
 * This class represents  the natural logarithm (base e) of a double value.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class Log extends FunctionsWithOneArgument {

	public Log(){
		this.identifier = "log";
	}
	
	@Override
	public double evaluate(double argument) {
		return Math.log(argument);
	}
	
	
}
