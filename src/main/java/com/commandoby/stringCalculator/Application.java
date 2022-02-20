package com.commandoby.stringCalculator;

import java.util.Scanner;

class Application {
	private static final String HELP = "exit - complete the program;\n"
			+ "help - help for valid commands.";
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String text;
		boolean active = true;
		
		while (active) {
			System.out.print("Enter the equation: ");
			text = scanner.nextLine().trim();
			
			if (text.equals("help")) {
				System.out.println(HELP);
			}
			if (text.equals("exit")) {
				active = false;
				scanner.close();
			}
		}
	}
}