package com.commandoby.stringCalculator.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.OperationType;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Writer;

public class WriterImpl implements Writer {
	public static int numbersAfterTheDecimalPoint = 5;
	private static List<BigDecimal> decimalList = new ArrayList<>();

	@Override
	public String write(Operand operand) throws WriteException {
		StringBuilder sb = new StringBuilder();

		updateDecimalList();

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
	public String writeOperandNumber(BigDecimal number) {
		int i = 0;

		while (i < numbersAfterTheDecimalPoint
				&& number.remainder(decimalList.get(i)).compareTo(BigDecimal.ZERO) != 0) {
			i++;
		}

		return number.setScale(i, RoundingMode.HALF_UP).toString();
	}

	private static void updateDecimalList() {
		while (decimalList.size() < numbersAfterTheDecimalPoint) {
			decimalList.add(new BigDecimal(1).divide(new BigDecimal(10).pow(decimalList.size())));
		}
	}
}
