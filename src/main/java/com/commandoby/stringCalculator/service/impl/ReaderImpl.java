package com.commandoby.stringCalculator.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.service.Reader;

public class ReaderImpl implements Reader {
	private Operand targetOperand = new Operand(Operation.ADD, 0);

	public List<Operand> read(String text) {
		List<Operand> operandList = new ArrayList<>();
		operandList.add(new Operand(Operation.ADD, 0));

		for (int i = 0; i < text.length(); i++) {
			String sumbol = text.substring(i, i+1);

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
				targetOperand = new Operand(Operation.ADD, 0);
				continue;
			}
		}
		return operandList;
	}

	private void readOperation(String sumbol) {
		switch (sumbol) {
		case "+": 
			targetOperand.setOperation(Operation.ADD);
			break;
		case "-": 
			targetOperand.setOperation(Operation.SUBTRACT);
			break;
		case "*": 
			targetOperand.setOperation(Operation.MULTIPLY);
			break;
		case "/": 
			targetOperand.setOperation(Operation.DIVIDE);
			break;
		case "^": 
			targetOperand.setOperation(Operation.EXPONENTIETION);
			break;
		}
	}

	private int readNumber(String text, int i) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(text);
		matcher.find(i);
		String numberString = matcher.group();
		targetOperand.setOperandNumber(Double.valueOf(numberString));
		return matcher.end();
	}

}
