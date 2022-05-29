package com.commandoby.stringCalculator.service.impl;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Writer;

public class WriterImpl implements Writer {
	public static int numbersAfterTheDecimalPoint = 2;

	@Override
	public String write(Operand operand) throws WriteException {
		Writer writer = new WriterImpl();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < operand.size(); i++) {
			Operand subOperand = operand.get(i);

			if (subOperand.getOperation() != null) {
				sb.append(writeOperation(subOperand.getOperation()));
			}
			if (subOperand.size() == 0) {
				sb.append(writeOperandNumber(subOperand.getOperandNumber()));
			} else {
				sb.append("(" + writer.write(subOperand) + ")");
			}
		}
		return sb.substring(0);
	}

	@Override
	public String writeOperandNumber(double number) {
		if (number % 1 == 0) {
			return String.format("%1.0f", number);
		}
		return String.format("%1." + numbersAfterTheDecimalPoint + "f", number);
	}

	private String writeOperation(Operation operation) throws WriteException {
		if (operation.equals(Operation.EXPONENTIETION)) {
			return "^";
		}
		if (operation.equals(Operation.MULTIPLY)) {
			return " * ";
		}
		if (operation.equals(Operation.DIVIDE)) {
			return " / ";
		}
		if (operation.equals(Operation.ADD)) {
			return " + ";
		}
		if (operation.equals(Operation.SUBTRACT)) {
			return " - ";
		}
		throw new WriteException("Operation read error.");
	}
}
