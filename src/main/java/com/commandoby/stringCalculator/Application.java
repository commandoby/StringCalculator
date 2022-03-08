package com.commandoby.stringCalculator;

import java.util.Scanner;

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

class Application {
	private static final String HELP = "exit - complete the program;\n" + "help - help for valid commands;\n"
			+ "point - numbers after the decimal point (Default: 2);\n" + "more on/more off - Detailed solution.\n"
			+ "Example: 1.5 * (2 - 3) + 4^0.5";

	private static Reader reader = new ReaderImpl();
	private static Solver solver = new SolverImpl();
	private static Writer writer = new WriterImpl();
	private static Logger log = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String text;
		boolean active = true;

		while (active) {
			try {
				System.out.print("Enter the equation: ");
				text = scanner.nextLine().trim();

				switch (text) {
				case "help":
					System.out.println(HELP);
					break;
				case "exit":
					active = false;
					scanner.close();
					break;
				case "point":
					System.out.print("Enter the number of digits after the decimal point: ");
					WriterImpl.numbersAfterTheDecimalPoint = Integer.parseInt(scanner.nextLine().trim());
					break;
				case "more on":
					System.out.println("Detailed solution included.");
					SolverImpl.detailedSolution = true;
					break;
				case "more off":
					System.out.println("Detailed solution is off.");
					SolverImpl.detailedSolution = false;
					break;
				default:
					Operand operand = new Operand(null, 0, reader.read(text));
					String equation = writer.write(operand);
					double answer = solver.solve(operand);
					System.out.println(equation + " = " + writer.writeOperandNumber(answer));
				}
			} catch (InvalidCharacterException | ConflictOfOperationsException | SubEquationException e) {
				log.error(e);
			} catch (WriteException e) {
				log.error(e);
			} catch (NumberFormatException e) {
				log.error(e);
			}
		}
	}
}