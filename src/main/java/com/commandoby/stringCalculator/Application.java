package com.commandoby.stringCalculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			+ "point (n) - numbers after the decimal point (Default: 2);\n" + "more on/more off - Detailed solution.\n"
			+ "Example: 1.5 * (2 - 3) + 4^0.5\n";
	public static final String START = "Welcome to the program for calculating equations.\n";

	private static Reader reader = new ReaderImpl();
	private static Solver solver = new SolverImpl();
	private static Writer writer = new WriterImpl();
	private static Logger log = Logger.getLogger(Application.class);
	private static Scanner scanner = new Scanner(System.in);
	
	public static boolean console = false;
	public static String consoleText = START + "\n";

	public static void main(String[] args) {

		if (console || args.length > 0 && args[0].equalsIgnoreCase("console")) {
			String text = null;
			console = true;
			System.out.println(START);
			while (console) {
				System.out.print("Enter the equation: ");
				text = scanner.nextLine().trim();

				System.out.println(textAnalysis(text));
			}
		} else {
			SwingUtilities.invokeLater(new ViewConsoleSwing()::run);
		}
	}

	public static String textAnalysis(String text) {
		if (text.matches("help")) {
			return HELP;
		}
		if (text.matches("exit")) {
			scanner.close();
			System.exit(1);
			return "";
		}
		if (text.matches("more on")) {
			SolverImpl.detailedSolution = true;
			return "Detailed solution included.\n";
		}
		if (text.matches("more off")) {
			SolverImpl.detailedSolution = false;
			return "Detailed solution is off.\n";
		}
		if (text.matches("point\\s\\d+")) {
			Matcher matcher = Pattern.compile("\\d+").matcher(text);
			if (matcher.find()) {
				Integer pointNumber = Integer.valueOf(matcher.group());
				WriterImpl.numbersAfterTheDecimalPoint = pointNumber;
			}
			return "The number of decimal places is set to " + WriterImpl.numbersAfterTheDecimalPoint + ".\n";
		}
		processAnswer(text);
		return "";
	}

	private static void processAnswer(String text) {
		double answer = 0;

		try {
			answer = getAnswer(text);

			Operand operand = new Operand(null, 0);
			operand.addAll(reader.read(text));
			String answerText = writer.write(operand) + " = " + writer.writeOperandNumber(answer);
			print(answerText + "\n");
		} catch (InvalidCharacterException | ConflictOfOperationsException | SubEquationException | WriteException
				| NumberFormatException e) {
			log.error(e);
			printOnlySwing(e.toString() + "\n");
		}
	}

	static double getAnswer(String text)
			throws InvalidCharacterException, ConflictOfOperationsException, SubEquationException {
		Operand operand = new Operand(null, 0);
		operand.addAll(reader.read(text));
		return solver.solve(operand);
	}

	public static void print(String text) {
		consoleText += text;
		if (console) {
			System.out.print(text);
		} else {
			ViewConsoleSwing.print();
		}
	}
	
	public static void printOnlySwing(String text) {
		consoleText += text;
		if (!console) {
			ViewConsoleSwing.print();
		}
	}
}