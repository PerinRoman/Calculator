package ua.perin.roman.math.functions;


/**
 * This class must be extended by all classes that 
 * represent function with one argument.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public abstract class FunctionsWithOneArgument {
	
	String identifier;
	
	public String getIdentificator(){
		return this.identifier;
	}
	
	public abstract double evaluate(double argument);
	
}
