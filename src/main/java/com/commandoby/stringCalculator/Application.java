package com.commandoby.stringCalculator;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.exceptions.ConflictOfOperationsException;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.exceptions.SubEquationException;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Reader;
import com.commandoby.stringCalculator.service.Solver;
import com.commandoby.stringCalculator.service.Writer;
import com.commandoby.stringCalculator.service.impl.ReaderImpl;
import com.commandoby.stringCalculator.service.impl.SolverImpl;
import com.commandoby.stringCalculator.service.impl.WriterImpl;
import com.commandoby.stringCalculator.swing.ViewConsoleSwing;

public class Application {
	private static final String HELP = "exit - complete the program;\n" + "help - help for valid commands;\n"
			+ "point - numbers after the decimal point (Default: 2);\n" + "more on/more off - Detailed solution.\n"
			+ "Example: 1.5 * (2 - 3) + 4^0.5\n";
	public static final String START = "Welcome to the program for calculating equations.\n";

	private static Reader reader = new ReaderImpl();
	private static Solver solver = new SolverImpl();
	private static Writer writer = new WriterImpl();
	private static Logger log = Logger.getLogger(Application.class);

	static Scanner scanner = new Scanner(System.in);
	static boolean active = true;
	public static boolean console = false;

	public static void main(String[] args) {
		String text;

		if (args.length > 0 && args[0].equalsIgnoreCase("console")) {
			console = true;
			System.out.println(START);
			while (active) {
				System.out.print("Enter the equation: ");
				text = scanner.nextLine().trim();

				System.out.println(textAnalysis(text));
			}
		} else {
			SwingUtilities.invokeLater(new ViewConsoleSwing()::run);
		}
	}

	public static String textAnalysis(String text) {
		switch (text) {
		case "help":
			return HELP;
		case "exit":
			active = false;
			scanner.close();
			return "";
		case "point":
			if (console) {
			System.out.print("Enter the number of digits after the decimal point: ");
			WriterImpl.numbersAfterTheDecimalPoint = Integer.parseInt(scanner.nextLine().trim());
			return "The number of decimal places is set to " + WriterImpl.numbersAfterTheDecimalPoint + ".\n";
			} else {
				return "Sorry. This command don't work without console. =(";
			}
		case "more on":
			SolverImpl.detailedSolution = true;
			return "Detailed solution included.\n";
		case "more off":
			SolverImpl.detailedSolution = false;
			return "Detailed solution is off.\n";
		default:
			getAnswer(text);
			return "";
		}
	}

	public static double getAnswer(String text) {
		double answer = 0;

		try {
			Operand operand = new Operand(null, 0, reader.read(text));
			String equation = writer.write(operand);
			answer = solver.solve(operand);

			String answerText = equation + " = " + writer.writeOperandNumber(answer);
			if (console) {
				System.out.println(answerText);
			} else {
				ViewConsoleSwing.addConsoleLine(answerText);
			}
		} catch (InvalidCharacterException | ConflictOfOperationsException | SubEquationException | WriteException
				| NumberFormatException e) {
			log.error(e);
			if (!console) {
				ViewConsoleSwing.addConsoleLine(e.toString());
			}
		}

		return answer;
	}
}