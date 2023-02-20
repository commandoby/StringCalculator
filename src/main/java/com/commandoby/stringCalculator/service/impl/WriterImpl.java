package com.commandoby.stringCalculator.service.impl;

import java.math.BigDecimal;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.OperationType;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Writer;

public class WriterImpl implements Writer {
	public static int numbersAfterTheDecimalPoint = 5;

	@Override
	public String write(Operand operand) throws WriteException {
		StringBuilder sb = new StringBuilder();

		for (Operand subOperand : operand) {

			if (subOperand.getOperation() != null && subOperand.getOperation().getType() != OperationType.LAST) {
				sb.append(subOperand.getOperation().getText());
			}
			if (subOperand.isEmpty()) {
				sb.append(writeOperandNumber(subOperand.getOperandNumber()));
			} else {
				Writer writer = new WriterImpl();
				if (subOperand.size() > 1) {
					sb.append("(" + writer.write(subOperand) + ")");
				} else {
					sb.append(writer.write(subOperand));
				}
			}
			if (subOperand.getOperation() != null && subOperand.getOperation().getType() == OperationType.LAST) {
				sb.append(subOperand.getOperation().getText());
			}
		}
		return sb.substring(0);
	}

	@Override
	public String writeOperandNumber(double number) {
		BigDecimal bigNumber = new BigDecimal(number);
		int i = 0;

		while (bigNumber.remainder(new BigDecimal(1).divide(new BigDecimal(10).pow(i))).compareTo(BigDecimal.ZERO) != 0
				&& i < numbersAfterTheDecimalPoint) {
			i++;
		}

		return String.format("%1." + i + "f", number);
	}
}
