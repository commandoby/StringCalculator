package com.commandoby.stringCalculator.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.OperationType;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Writer;

public class WriterImpl implements Writer {
	public static int scale = 5;

	@Override
	public String write(Operand operand) throws WriteException {
		StringBuilder sb = new StringBuilder();

		for (Operand subOperand : operand) {
			if (subOperand.getOperandNumber() != null && subOperand.size() > 0) {
				throw new WriteException(
						"Number: " + subOperand.getOperandNumber() + " and list size " + subOperand.size());
			}

			if (subOperand.getOperation() != null && subOperand.getOperation().getType() != OperationType.LAST) {
				sb.append(subOperand.getOperation().getText());
			}
			if (subOperand.getOperation() == null || subOperand.getOperation().getType() != OperationType.SPECIFIC) {
				if (subOperand.isEmpty()) {
					sb.append(writeOperandNumber(subOperand.getOperandNumber()).toString());
				} else {
					Writer writer = new WriterImpl();
					if (subOperand.size() > 1) {
						sb.append("(" + writer.write(subOperand) + ")");
					} else {
						sb.append(writer.write(subOperand));
					}
				}
			}
			if (subOperand.getOperation() != null && subOperand.getOperation().getType() == OperationType.LAST) {
				sb.append(subOperand.getOperation().getText());
			}
		}
		return sb.substring(0);
	}

	@Override
	public BigDecimal writeOperandNumber(BigDecimal number) {
		if (number.remainder(new BigDecimal(1)).compareTo(BigDecimal.ZERO) == 0) {
			return number.setScale(0);
		}
		return number.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros();
	}
}
