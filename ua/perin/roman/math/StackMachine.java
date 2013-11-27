package ua.perin.roman.math;

import java.util.List;
import java.util.Stack;

/**
 * This class implements the algorithm for evaluating postfix expressions.
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class StackMachine {

	private static Stack<Double> numberStack = new Stack<Double>();

	public static double evaluatingReversePolishNotation(
			List<String> reversePolishNotation) {
		
		List<String> rpn  =  reversePolishNotation;
		String token 	  =  "";
		double firstArg   =  0d;
		double secondArg  =  0d;

		lookToNextToken: while (!rpn.isEmpty()) {
			token = rpn.get(0);
			
			if (MathElements.isNumber(token)) {
				numberStack.push(Double.valueOf(token));
				
				rpn.remove(0);
				continue lookToNextToken;
			}
			
			if (MathElements.isOperator(token)) {
				secondArg = numberStack.pop();
				firstArg = numberStack.pop();
				numberStack.push(MathElements.evaluateOperation(token,
						firstArg, secondArg));
				
				rpn.remove(0);
				continue lookToNextToken;
			}
			
			if (MathElements.isOneArgFunction(token)) {
				firstArg = numberStack.pop();
				numberStack.push(MathElements.evaluateOneArgFunction(token,
						firstArg));
				
				rpn.remove(0);
				continue lookToNextToken;
			}
			
			if (MathElements.isTwoArgFunction(token)) {
				firstArg = numberStack.pop();
				secondArg = numberStack.pop();
				numberStack.push(MathElements.evaluateTwoArgFunction(token,
						firstArg, secondArg));
				
				rpn.remove(0);
				continue lookToNextToken;
			}
		}
		return numberStack.pop();
	}
}
