package com.commandoby.stringCalculator.service;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.exceptions.WriteException;

public interface Writer {
	String write(Operand operand) throws WriteException;
	
	String writeOperandNumber(double number);
}
