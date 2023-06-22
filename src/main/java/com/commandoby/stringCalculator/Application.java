package com.commandoby.stringCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.service.*;
import com.commandoby.stringCalculator.service.impl.*;
import com.commandoby.stringCalculator.swing.ViewConsoleSwing;

public class Application {
	public static final String START = "Welcome to the program for calculating equations. For help write 'help'. v1.04\n";

	private static Reader reader = new ReaderImpl();
	private static Solver solver = new SolverImpl();
	private static Writer writer = new WriterImpl();
	private static Logger log = Logger.getLogger(Application.class);
	private static Scanner scanner = new Scanner(System.in);

	public static boolean console = false;

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
			return readOfHelp();
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
		if (text.matches("scale\\s*\\d+")) {
			Matcher matcher = Pattern.compile("\\d+").matcher(text);
			if (matcher.find()) {
				Integer pointNumber = Integer.valueOf(matcher.group());
				WriterImpl.scale = pointNumber;
			}
			return "The number of decimal places is set to " + WriterImpl.scale + ".\n";
		}
		processAnswer(text);
		return "";
	}

	private static void processAnswer(String text) {
		try {
			Operand operand = reader.read(text);
			BigDecimal answer = solver.solve(operand.clone());
			String answerText = writer.write(operand) + " = " + writer.writeOperandNumber(answer);
			print(answerText + "\n");
		} catch (Exception e) {
			log.error("Error text: " + text);
			log.error(e);
			if (!console) {
				ViewConsoleSwing.print(e.toString() + "\n");
			}
		}
	}

	public static void print(String text) {
		if (console) {
			System.out.print(text);
		} else {
			ViewConsoleSwing.print(text);
		}
	}

	private static String readOfHelp() {
		InputStreamReader inputStreamReader = new InputStreamReader(
				ClassLoader.getSystemClassLoader().getResourceAsStream("Help.txt"));
		StringBuilder stringBuilder = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
		} catch (IOException e) {
			log.error(e);
			if (!console) {
				ViewConsoleSwing.print(e.toString() + "\n");
			}
		}

		return stringBuilder.toString();
	}
}