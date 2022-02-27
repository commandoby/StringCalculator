package com.commandoby.stringCalculator.service;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.exceptions.ConflictOfOperationsException;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;

public interface Reader {
	Operand read(String text) throws InvalidCharacterException, ConflictOfOperationsException;
}
