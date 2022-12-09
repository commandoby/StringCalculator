package com.commandoby.stringCalculator.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.exceptions.ConflictOfOperationsException;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.exceptions.SubEquationException;
import com.commandoby.stringCalculator.service.Reader;

import static com.commandoby.stringCalculator.enums.Operation.*;

public class ReaderImpl implements Reader {
	private Operand inclusiveOperand = new Operand(null, 0);
	boolean negative = false;
	public static final Map<Operation, String> symbolsOfOperations = new HashMap<>();
	
	{
		symbolsOfOperations.put(ADD, " + ");
		symbolsOfOperations.put(SUBTRACT, " - ");
		symbolsOfOperations.put(MULTIPLY, " * ");
		symbolsOfOperations.put(DIVIDE, " / ");
		symbolsOfOperations.put(EXPONENTIETION, "^");
	}

	@Override
	public Operand read(String text)
			throws InvalidCharacterException, ConflictOfOperationsException, SubEquationException {
		Operand operand = new Operand(null, 0);

		/*for (int i = 0; i < text.length(); i++) {
			String sumbol = text.substring(i, i + 1);

			if (sumbol.equals(" ")) {
				continue;
			}
			if (sumbol.matches("(\\+|-|\\*|/|\\^)")) {
				readOperation(sumbol);
				continue;
			}
			if (sumbol.matches("\\d")) {
				i = readNumber(text, i) - 1;
				operand.add(inclusiveOperand);
				inclusiveOperand = new Operand(null, 0);
				continue;
			}
			if (sumbol.matches("\\(")) {
				i = readSubEquation(text, i + 1);
				operand.add(inclusiveOperand);
				inclusiveOperand = new Operand(null, 0);
				continue;
			}
			if (sumbol.matches("\\)")) {
				throw new SubEquationException("Missing opening bracket.");
			}
			throw new InvalidCharacterException("Invalid character: " + sumbol);
		}*/
		/*\\s*\\D*\\s*\\d+(\\.|,)?\\d*/
		String[] operandsTexts = text.split("\\d");

	System.out.println(operandsTexts.length);
		/*if (operand.get(0).getOperation() != null && operand.get(0).getOperation().equals(SUBTRACT)
				&& operand.get(0).getOperandNumber() > 0 && operand.get(0).size() == 0) {
			operand.get(0).setOperandNumber(operand.get(0).getOperandNumber() * (-1));
			operand.get(0).setOperation(null);
		}*/
		
				return operand;
	}

	private void readOperation(String symbol) throws InvalidCharacterException, ConflictOfOperationsException {
		for (Map.Entry<Operation, String> entrySymbol: symbolsOfOperations.entrySet()) {
			if (entrySymbol.getValue().trim().equals(symbol)) {
				checkAndSetOperation(entrySymbol.getKey());
				return;
			}
		}
		throw new InvalidCharacterException("Invalid character: " + symbol);
	}

	private void checkAndSetOperation(Operation operation) throws ConflictOfOperationsException {
		if (operation.equals(SUBTRACT) && inclusiveOperand.getOperation() != null && !negative) {
			negative = true;
			return;
		}

		if (inclusiveOperand.getOperation() == null) {
			inclusiveOperand.setOperation(operation);
		} else {
			throw new ConflictOfOperationsException(
					"Conflict of operations: " + inclusiveOperand.getOperation().name() + " and " + operation.name());
		}
	}

	private int readNumber(String text, int i) {
		Pattern pattern = Pattern.compile("\\d+(\\.|,)?\\d*");
		Matcher matcher = pattern.matcher(text);
		matcher.find(i);
		String numberString = matcher.group();

		Pattern decimalPointPattern = Pattern.compile(",");
		Matcher decimalPointMatcher = decimalPointPattern.matcher(numberString);
		String numberStringWithoutDecimalPoint = decimalPointMatcher.replaceAll(".");

		double value = Double.valueOf(numberStringWithoutDecimalPoint);
		if (negative) {
			value *= -1;
		}
		negative = false;
		inclusiveOperand.setOperandNumber(value);
		return matcher.end();
	}

	private int readSubEquation(String text, int startPoint)
			throws InvalidCharacterException, ConflictOfOperationsException, SubEquationException {
		Reader reader = new ReaderImpl();
		int subEquationLevel = 0;

		for (int i = startPoint; i < text.length(); i++) {
			String sumbol = text.substring(i, i + 1);

			if (sumbol.matches("\\(")) {
				subEquationLevel++;
			}
			if (sumbol.matches("\\)")) {
				if (subEquationLevel == 0) {
					inclusiveOperand = reader.read(text.substring(startPoint, i));
					return i;
				} else {
					subEquationLevel--;
				}
			}
		}
		throw new SubEquationException("Missing closing bracket.");
	}

}
