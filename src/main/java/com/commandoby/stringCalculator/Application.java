package com.commandoby.stringCalculator;

import java.util.List;
import java.util.Scanner;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.service.Reader;
import com.commandoby.stringCalculator.service.impl.ReaderImpl;

class Application {
	private static final String HELP = "exit - complete the program;\n"
			+ "help - help for valid commands.";
	
	private static Reader reader = new ReaderImpl();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String text;
		boolean active = true;

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
			}
		}
	}
}