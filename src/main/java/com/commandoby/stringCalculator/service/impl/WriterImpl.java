package com.commandoby.stringCalculator.service.impl;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.OperationType;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Writer;

public class WriterImpl implements Writer {
	public static int numbersAfterTheDecimalPoint = 2;

	@Override
	public String write(Operand operand) throws WriteException {
		StringBuilder sb = new StringBuilder();

		for (Operand subOperand: operand) {

			if (subOperand.getOperation() != null && subOperand.getOperation().getType()!=OperationType.LAST) {
				sb.append(subOperand.getOperation().getText());
			}
			if (subOperand.isEmpty()) {
				sb.append(writeOperandNumber(subOperand.getOperandNumber()));
			} else {
				Writer writer = new WriterImpl();
				if (subOperand.size()>1) {
				sb.append("(" + writer.write(subOperand) + ")");
				} else {
					sb.append(writer.write(subOperand));
				}
			}
			if (subOperand.getOperation() != null && subOperand.getOperation().getType()==OperationType.LAST) {
				sb.append(subOperand.getOperation().getText());
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
}
