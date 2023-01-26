package com.commandoby.stringCalculator.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.exceptions.SubEquationException;
import com.commandoby.stringCalculator.service.Reader;

import static com.commandoby.stringCalculator.enums.Operation.*;

public class ReaderImpl implements Reader {
	private Operand inclusiveOperand = new Operand(null, 0);
	boolean negative = false;
	private String currentText;
	public static final Map<Operation, String> symbolsOfOperations = new HashMap<>();

	{
		symbolsOfOperations.put(ADD, " + ");
		symbolsOfOperations.put(SUBTRACT, " - ");
		symbolsOfOperations.put(MULTIPLY, " * ");
		symbolsOfOperations.put(DIVIDE, " / ");
		symbolsOfOperations.put(EXPONENTIETION, "^");
	}

	@Override
	public Operand read(String text) throws InvalidCharacterException, SubEquationException {
		currentText = text;
		System.out.println("Start " + text);
		Operand operand = new Operand(null, 0);
		List<String> textOperands = split();

		for (String s : textOperands) {
			System.out.println("a " + s);
			if (s.matches(".*\\(+.*")) {
				inclusiveOperand = read(s.substring(0, s.length() - 1).replaceFirst("[^0-9\\s]*\\s*\\(", ""));
				readOperation(s, Pattern.compile("\\s*[^0-9\\(]+\\s*\\("));
			} else {
				readNumber(s);
				readOperation(s, Pattern.compile("\\s*[^0-9\\(]+\\s*\\d"));
			}
			operand.add(inclusiveOperand);
			inclusiveOperand = new Operand(null, 0);
		}

		if (operand.get(0).getOperation() != null && operand.get(0).getOperation().equals(SUBTRACT)
				&& operand.get(0).getOperandNumber() > 0 && operand.get(0).size() == 0) {
			operand.get(0).setOperandNumber(operand.get(0).getOperandNumber() * (-1));
			operand.get(0).setOperation(null);
		}

		return operand;
	}

	private List<String> split() {
		Map<Integer, String> mapOfTextOperands = new HashMap<>();

		splitOfSubEquations(mapOfTextOperands);
		splitOfNumbers(mapOfTextOperands);

		Stream<Map.Entry<Integer, String>> stream = mapOfTextOperands.entrySet().stream()
				.sorted(new Comparator<Map.Entry<Integer, String>>() {
					@Override
					public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
						return o1.getKey().compareTo(o2.getKey());
					}
				});
		return stream.map(Map.Entry::getValue).toList();
	}

	private void splitOfSubEquations(Map<Integer, String> map) {
		String newText = currentText;
		Pattern patternOfStart = Pattern.compile("[^0-9\\)\\s]*\\s*\\(");
		Matcher matcherOfStart = patternOfStart.matcher(currentText);

		if (matcherOfStart.find()) {
			List<Integer> countOfOpening = new ArrayList<>();
			List<Integer> countOfClosing = new ArrayList<>();

			for (int i = matcherOfStart.start(); i < currentText.length(); i++) {
				String sumbol = currentText.substring(i, i + 1);

				if (sumbol.matches("\\(")) {
					countOfOpening.add(i);
					continue;
				}
				if (sumbol.matches("\\)")) {
					countOfClosing.add(i);
				}
				if (countOfClosing.size() > 0 && countOfOpening.size() == countOfClosing.size()) {
					break;
				}
			}

			String subText = currentText.substring(matcherOfStart.start(),
					countOfClosing.get(countOfClosing.size() - 1) + 1);
			map.put(matcherOfStart.start(), subText);

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < subText.length(); i++) {
				sb.append(" ");
			}
			newText = newText.replaceFirst(Pattern.quote(subText), sb.toString());
			System.out.println("b " + matcherOfStart.start() + " " + subText + " |" + newText + "|");
			currentText = newText;
			splitOfSubEquations(map);
		}
	}

	private void splitOfNumbers(Map<Integer, String> map) {
		System.out.println("c " + currentText);
		Pattern pattern = Pattern.compile("[^0-9\\s]*\\s*\\d+(\\.|,)?\\d*");
		Matcher matcher = pattern.matcher(currentText);
		while (matcher.find()) {
			System.out.println("d " + matcher.start());
			map.put(matcher.start(), matcher.group());
		}
	}

	private void readOperation(String text, Pattern pattern) throws InvalidCharacterException {
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			String symbol = matcher.group().trim();
			for (Map.Entry<Operation, String> entrySymbol : symbolsOfOperations.entrySet()) {
				Matcher symbolMatcher = Pattern.compile(Pattern.quote(entrySymbol.getValue().trim())).matcher(symbol);
				if (symbolMatcher.find()) {
					checkAndSetOperation(entrySymbol.getKey());
					return;
				}
			}
			throw new InvalidCharacterException("Invalid character: " + symbol);
		}
	}

	private void checkAndSetOperation(Operation operation) {
		if (operation.equals(SUBTRACT) && inclusiveOperand.getOperation() != null && !negative) {
			negative = true;
			return;
		}
		inclusiveOperand.setOperation(operation);
	}

	private void readNumber(String text) {
		Pattern pattern = Pattern.compile("\\d+(\\.|,)?\\d*");
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
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
		}
	}
}
