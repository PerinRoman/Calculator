package ua.perin.roman.math;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * This class implements the shunting-yard algorithm 
 * to convert infix expressions to postfix (RPN). 
 * <p>
 * @author Perin Roman
 * @version 1.0
 */
public class ReversePolishNotationConverter {

	private static 	String originExpression;
	private static 	String preparedExpression;
	private static  List<String> output		     = new LinkedList<>();
	private static Stack<String> stackOperators  = new Stack<String>();
	private static 	int    indexOfCurrentToken   = 0;
	private static  String token 				 = "";
	private static  String previousToken 		 = "";

	public static List<String> sortingStation(String inputExpression)
			throws IOException {

		if (inputExpression == null || inputExpression.length() == 0)
			throw new IOException("Mathematical expression isn't specified.");

		originExpression = inputExpression;
		preparedExpression = doPreparations(inputExpression);
		
		//reset data
		stackOperators.clear();
		output.clear();
		indexOfCurrentToken = 0;
		token = "";
		previousToken = "";

		return sortingStation();
	}

	private static List<String> sortingStation() throws IOException {

		StringTokenizer tokenizer = MathElements.getTokenizer(preparedExpression);

		if (tokenizer.countTokens() < 2) {
			throw new IOException("incomplete expression");
		}

		analyze_next_token: while (tokenizer.hasMoreTokens()) {
			previousToken = token;
			token = tokenizer.nextToken();
			indexOfCurrentToken++;

			// Two adjacent tokens are checking on compatibility
			checkTokens(previousToken, token);

			if (MathElements.isNumber(token)) {
				output.add(token);
				continue analyze_next_token;
			}
			if (token.equals("(")) {
				stackOperators.push(token);
				continue analyze_next_token;
			}
			if (MathElements.isFunction(token)) {
				stackOperators.push(token);
				continue analyze_next_token;
			}
			if (token.equals(",")) {
				while (!stackOperators.isEmpty()
						&& !"(".equals(stackOperators.peek())) {
					output.add(stackOperators.pop());
				}
				stackOperators.push(token);
				continue analyze_next_token;
			}
			
			// If the token is a right parenthesis:
			// 1) Until the token at the top of the stack is a left parenthesis,
			//    pop operators off the stack onto the output queue.
			// 2) Pop the left parenthesis from the stack, but not onto the output queue.
			// 3) If the token at the top of the stack is a function token, 
			//    pop it onto the output queue.
			// 4) If the stack runs out without finding a left parenthesis, 
			//    then there are mismatched parentheses.
			if (token.equals(")")) {
				byte delimiterCounter = 0;
				String top = "";
				while (!stackOperators.empty()) {
					top = stackOperators.pop();
					if (top.equals(",")) {
						delimiterCounter++;
						continue;
					}
					if (!top.equals("(")) {
						output.add(top);
					} else {
						//Checking element before "(".
						caseNoFunctions(delimiterCounter);
						caseOneArgFunction(delimiterCounter);
						caseTwoArgFunction(delimiterCounter);
						continue analyze_next_token;
					}
				}
				bugNoLeftBracket();
			}
			
			// If the token is an operator, "o1", and while at the top of the stack
			// is some operator, "o2", with a higher priority than "o1" then:
			// pop "o2" off the stack, onto the output queue;
			// else push "o1" onto the stack.
			if (MathElements.isOperator(token)) {

				while (!stackOperators.isEmpty()
						&& MathElements.isOperator(stackOperators.peek())
						&& MathElements.priorityComparator(token,
								stackOperators.peek()) <= 0) {

					output.add(stackOperators.pop());
				}
				stackOperators.push(token);
				continue analyze_next_token;
			}

			errorUnknownArgument(token);
		}
		
		// Last element of input expression is checking on validity.
		if (!(MathElements.isNumber(token) || token.equals(")"))) {
			throw new IOException("Incorrect last argument: \"" + token + "\"");
		}
		
		// When there are no more tokens to read &
		// while there are still operator tokens in the stack:
		while (!stackOperators.isEmpty()) {
			if ("(".equals(stackOperators.peek())) {
				errorNoRightBracket();
			} else {
				output.add(stackOperators.pop());
			}
		}
		
		return output;
	}

	private static void checkTokens(String previousToken, String currentToken)
			throws IOException {

		if (!previousToken.isEmpty()) {
			checkAdjacentTokens(previousToken, currentToken);
		} else if (MathElements.isOperator(currentToken)
				|| currentToken.equals(")") || currentToken.equals(",")) {
			throw new IOException("Incorrect first argument: \""
								  + currentToken	+ "\"");
		}
	}

	private static void caseOneArgFunction(int delimiterCounter)
			throws IOException {

		if (!stackOperators.empty()
				&& MathElements.isOneArgFunction(stackOperators.peek())) {
			if (delimiterCounter != 0) {
				bugIllegalNumberOfArguments();
			} else {
				output.add(stackOperators.pop());
			}
		}
	}

	private static void caseTwoArgFunction(int delimiterCounter)
			throws IOException {

		if (!stackOperators.empty()
				&& MathElements.isTwoArgFunction(stackOperators.peek())) {
			if (delimiterCounter != 1) {
				bugIllegalNumberOfArguments();
			} else {
				output.add(stackOperators.pop());
			}
		}
	}

	private static void caseNoFunctions(int delimiterCounter)
			throws IOException {
		
		if (!stackOperators.empty() && (delimiterCounter != 0)
				&& !MathElements.isFunction(stackOperators.peek())) {
			
			bugIllegalNumberOfArguments();
		}
	}
	
	private static void checkAdjacentTokens(String previousToken,
			String currentToken) throws IOException {
		if ((previousToken.equals("(") && currentToken.equals(")"))
				|| (previousToken.equals("(") && currentToken.equals(","))
				|| (previousToken.equals("(") 
						&& MathElements.isOperator(currentToken))
				|| (previousToken.equals(")") && currentToken.equals("("))
				|| (previousToken.equals(")") && MathElements.isNumber(currentToken))
				|| (previousToken.equals(")") 
						&& MathElements.isOneArgFunction(currentToken))
				|| (previousToken.equals(")") 
						&& MathElements.isTwoArgFunction(currentToken))
				|| (previousToken.equals(",") && currentToken.equals(")"))
				|| (previousToken.equals(",") 
						&& MathElements.isOperator(currentToken))
				|| (MathElements.isOperator(previousToken) 
						&& MathElements.isOperator(currentToken))
				|| (MathElements.isOneArgFunction(previousToken) 
						&& !currentToken.equals("("))
				|| (MathElements.isTwoArgFunction(previousToken) 
						&& !currentToken.equals("("))) {

			int bugPosition = findBugPosition();
			throw new IOException("Uncorrect sequense of math elements \""
					+ previousToken + currentToken + "\" that in the positions ¹"
					+ (bugPosition - 1) + "-" + bugPosition);
		}
	}

	private static int findBugPosition() {
		
		String withoutSpaces = originExpression.replace(" ", "");
		
		StringTokenizer withoutSpacesTokenizer = MathElements
				.getTokenizer(withoutSpaces);
		StringTokenizer processedTokenizer = MathElements
				.getTokenizer(preparedExpression);
		
		String withoutSpacesToken = withoutSpacesTokenizer.nextToken();
		String processedToken = processedTokenizer.nextToken();
		int indexInWithoutSpaces = 1;
		int indexInPreparedExpression = 1;
		
		while (indexInWithoutSpaces < indexOfCurrentToken) {
			if (withoutSpacesToken.equals(processedToken)) {
				indexInPreparedExpression++;
				if (!withoutSpacesTokenizer.hasMoreTokens()) {
					break;
				}
				withoutSpacesToken = withoutSpacesTokenizer.nextToken();
			}
			indexInWithoutSpaces++;
			processedToken = processedTokenizer.nextToken();
		}
		
		return indexInPreparedExpression;
	}

	private static String doPreparations(String processingData) {
		
		if (processingData.charAt(0) == '-') {
			processingData = "0" + processingData;
		}
		
		processingData = processingData.replace(" ", "");
		processingData = processingData.replace("(-", "(0-");
		processingData = processingData.replace(",-", ",0-");
		
		return processingData;
	}

	private static void bugIllegalNumberOfArguments() throws IOException {
		int bugPosition = findBugPosition();
		throw new IOException(
				"Illegal number of arguments is inside parentheses at position #"
						+ bugPosition);
	}

	private static void bugNoLeftBracket() throws IOException {
		int bugPosition = findBugPosition();
		throw new IOException("There is a mistake in math expression: "
				+ "\")\", " + "that is in position #: " + bugPosition
				+ " don't " + "have appropriate left bracket \"(\" ");
	}

	private static void errorNoRightBracket() throws IOException {
		int bugPosition = findBugPosition();
		throw new IOException("There is a mistake in math expression: "
				+ " put right bracket \")\" " + "at last position (#: "
				+ (bugPosition + 1) + ") to complete expression");
	}

	private static void errorUnknownArgument(String unknownToken)
			throws IOException {
		int bugPosition = findBugPosition();
		throw new IOException("There is a mistake in math expression: "
				+ "unknown math element \"" + unknownToken + "\" at position #"
				+ bugPosition);
	}
}
