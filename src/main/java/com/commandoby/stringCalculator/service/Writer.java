package com.commandoby.stringCalculator.service;

import java.math.BigDecimal;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.exceptions.WriteException;

public interface Writer {
	String write(Operand operand) throws WriteException;

	BigDecimal writeOperandNumber(BigDecimal number);
}
