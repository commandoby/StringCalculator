package com.commandoby.stringCalculator.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.exceptions.ConflictOfOperationsException;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.service.Reader;

public class ReaderImpl implements Reader {
	private Operand targetOperand = new Operand(null, 0);
	boolean negative = false;

	@Override
	public List<Operand> read(String text) throws InvalidCharacterException, ConflictOfOperationsException {
		List<Operand> operandList = new ArrayList<>();

		for (int i = 0; i < text.length(); i++) {
			String sumbol = text.substring(i, i + 1);

			if (sumbol.equals(" ")) {
				continue;
			}
			if (sumbol.matches("(\\+|-|\\*|/|^)")) {
				readOperation(sumbol);
				continue;
			}
			if (sumbol.matches("\\d")) {
				i = readNumber(text, i) - 1;
				operandList.add(targetOperand);
				targetOperand = new Operand(null, 0);
				continue;
			}
			throw new InvalidCharacterException("Invalid character: " + sumbol);
		}
		return operandList;
	}

	private void readOperation(String sumbol) throws InvalidCharacterException, ConflictOfOperationsException {
		switch (sumbol) {
		case "+":
			checkAndSetOperation(Operation.ADD);
			break;
		case "-":
			checkAndSetOperation(Operation.SUBTRACT);
			break;
		case "*":
			checkAndSetOperation(Operation.MULTIPLY);
			break;
		case "/":
			checkAndSetOperation(Operation.DIVIDE);
			break;
		case "^":
			checkAndSetOperation(Operation.EXPONENTIETION);
			break;
		default:
			throw new InvalidCharacterException("Invalid character: " + sumbol);
		}
	}

	private void checkAndSetOperation(Operation operation) throws ConflictOfOperationsException {
		if (operation == Operation.SUBTRACT && targetOperand.getOperation() != null && !negative) {
			negative = true;
			return;
		}

		if (targetOperand.getOperation() == null) {
			targetOperand.setOperation(operation);
		} else {
			throw new ConflictOfOperationsException(
					"Conflict of operations: " + targetOperand.getOperation().name()
					+ " and " + operation.name());
		}
	}

	private int readNumber(String text, int i) {
		Pattern pattern = Pattern.compile("\\d+(\\.|,)?\\d*");
		Matcher matcher = pattern.matcher(text);
		matcher.find(i);
		String numberString = matcher.group();

		Pattern commaPattern = Pattern.compile(",");
		Matcher commaMatcher = commaPattern.matcher(numberString);
		String numberStringWithoutComma = commaMatcher.replaceAll(".");

		double value = Double.valueOf(numberStringWithoutComma);
		if (negative) {
			value *= -1;
		}
		negative = false;
		targetOperand.setOperandNumber(value);
		return matcher.end();
	}

}
