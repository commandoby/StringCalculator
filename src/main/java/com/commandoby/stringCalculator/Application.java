package com.commandoby.stringCalculator;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.exceptions.ConflictOfOperationsException;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.service.Reader;
import com.commandoby.stringCalculator.service.Solver;
import com.commandoby.stringCalculator.service.impl.ReaderImpl;
import com.commandoby.stringCalculator.service.impl.SolverImpl;

class Application {
	private static final String HELP = "exit - complete the program;\n" + "help - help for valid commands.";

	private static Reader reader = new ReaderImpl();
	private static Solver solver = new SolverImpl();
	private static Logger log = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String text;
		boolean active = true;

		try {
			while (active) {
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
				default:
					List<Operand> operandList = reader.read(text);
					System.out.println(operandList);
					double answer = solver.solve(operandList);
					System.out.println("Answer: " + answer);
				}
			}
		} catch (InvalidCharacterException | ConflictOfOperationsException e) {
			log.error(e);
		}
	}
}