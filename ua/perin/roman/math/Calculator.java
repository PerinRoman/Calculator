package ua.perin.roman.math;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Calculator - Reverse Polish notation common\scientific calculator.
 * <p>
 * @author Perin Roman
 * @version 1.0
 *
 */
public class Calculator {

	public static void main(String[] args) {

		String expression = "";
		Scanner in = new Scanner(System.in);

		System.out.println("Choose the type of calculator: \n"
				+ "for \"Common Calculator\" - enter \"C\" \n"
				+ "for \"Scientific Calculator\" - enter \"S\"");
				
		expression = in.nextLine();


		while (!(expression.equalsIgnoreCase("c") || expression.equalsIgnoreCase("s"))) {

			System.out.println("You have written: " + expression + "\n"
					+ "Select the calculator you want to work with: \n"
					+ "for \"Common Calculator\" - enter \"C\" \n"
					+ "for \"Scientific Calculator\" - enter \"S\" \n");
					
			expression = in.nextLine();
		}


		if (expression.equalsIgnoreCase("C")) {
			System.out.println("You have chosen \"Common Calculator\"");
			MathElements.setCommonCalculator();
		}

		if (expression.equalsIgnoreCase("S")) {
			System.out.println("You have chosen \"Scientific Calculator\"");
			MathElements.setScientificCalculator();
		}

		double result	 =  0d;
		List<String> RPN =  null;
		boolean flag	 =  true;

		while (flag) {
			System.out.println("Please, enter expression: \n");
			expression = in.nextLine();

			try {
				RPN = ReversePolishNotationConverter.sortingStation(expression);
				result = StackMachine.evaluatingReversePolishNotation(RPN);
				
				System.out.println("result = " + result + "\n");
				
			} catch (IOException e1) {
				System.out.println(e1.getMessage() + "\n");
			}

			System.out.println("Do You want to re-enter expression? \n"
					+ "\"Y\" - another calculation, or \n"
					+ "press any key to exit.");
			
			if (in.nextLine().equalsIgnoreCase("y")) {
				flag = true;
			} else {
				flag = false;
			}
		}
		in.close();
		
	}

}
