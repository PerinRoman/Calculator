package ua.perin.roman.math;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import ua.perin.roman.math.functions.*;

/**
 * MathElements - stores functions and operators that can be used
 * in expressions evaluations. 
 * Also it provides methods to work with defined functions and operations. 
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class MathElements {
	
	//Key - identifier of function with one argument; value - function with one argument.
	private static Map<String, FunctionsWithOneArgument> oneArgFunctions = new HashMap<>();
	
	//Key - identifier of function with two arguments; value - function with two arguments.
	private static Map<String, FunctionsWithTwoArguments> twoArgFunctions = new HashMap<>();
	
	//Key - identifier of operator. 
	//Value - priority. The higher the number, the higher the priority.
	private static Map<String, Integer> OPERATIONS = new HashMap<String, Integer>();
	
	public static boolean isOperator(String token) {
		return OPERATIONS.containsKey(token);
	}

	public static boolean isOneArgFunction(String token) {
		return oneArgFunctions.containsKey(token);
	}

	public static boolean isTwoArgFunction(String token) {
		return twoArgFunctions.containsKey(token);
	}

	public static boolean isFunction(String token) {
		return twoArgFunctions.containsKey(token) || oneArgFunctions.containsKey(token);
	}

	public static boolean isNumber(String token) {
		try {
			Double.parseDouble(token);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void addOneArgFunction(FunctionsWithOneArgument newFunction) {
		oneArgFunctions.put(newFunction.getIdentificator(), newFunction);
	}

	public static void addTwoArgFunction(FunctionsWithTwoArguments newFunction) {
		twoArgFunctions.put(newFunction.getIdentifier(), newFunction);
	}

	public static void setCommonCalculator() {
		OPERATIONS.put("^", 10);
		OPERATIONS.put("*", 9);
		OPERATIONS.put("/", 9);
		OPERATIONS.put("%", 9);
		OPERATIONS.put("+", 8);
		OPERATIONS.put("-", 8);
		addOneArgFunction(new Sqrt());
	}

	public static void setScientificCalculator() {
		OPERATIONS.put("^", 10);
		OPERATIONS.put("*", 9);
		OPERATIONS.put("/", 9);
		OPERATIONS.put("%", 9);
		OPERATIONS.put("+", 8);
		OPERATIONS.put("-", 8);
		addOneArgFunction(new ATan());
		addOneArgFunction(new Cbrt());
		addOneArgFunction(new Cos());
		addOneArgFunction(new Cosh());
		addOneArgFunction(new Exp());
		addOneArgFunction(new Log());
		addOneArgFunction(new Sin());
		addOneArgFunction(new Sinh());
		addOneArgFunction(new Sqrt());
		addOneArgFunction(new Tan());
		addOneArgFunction(new Tanh());
		addTwoArgFunction(new Max());
		addTwoArgFunction(new Min());
	}

	public static StringTokenizer getTokenizer(String input) {
		
		String delimiters = ",()";
		
		for (String operator : OPERATIONS.keySet()) {
			delimiters = delimiters + operator;
		}
		
		return new StringTokenizer(input, delimiters, true);
	}

	public static Double evaluateOperation(String token, Double firstArg,
			Double secondArg) {
		double result = 0d;
		switch (token) {
		case "+":
			result = firstArg + secondArg;
			break;
		case "-":
			result = firstArg - secondArg;
			break;
		case "*":
			result = firstArg * secondArg;
			break;
		case "/":
			result = firstArg / secondArg;
			break;
		case "%":
			result = firstArg % secondArg;
			break;
		case "^":
			result = Math.pow(firstArg, secondArg);
			break;
		default: 
			throw new IllegalArgumentException(token + "is unknown operator.");
		}
		return result;
	}

	public static Double evaluateOneArgFunction(String token, Double argument) {
		return oneArgFunctions.get(token).evaluate(argument);
	}

	public static Double evaluateTwoArgFunction(String token, Double firstArg, Double secondArg) {
		return twoArgFunctions.get(token).evaluate(firstArg, secondArg);
	}

	public static int priorityComparator(String operator1, String operator2) {
		int priority1 = OPERATIONS.get(operator1);
		int priority2 = OPERATIONS.get(operator2);
		
		return priority1 - priority2;
	}

}
