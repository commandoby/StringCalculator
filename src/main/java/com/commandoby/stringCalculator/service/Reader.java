package com.commandoby.stringCalculator.service;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.exceptions.SubEquationException;

public interface Reader {
	Operand read(String text) throws InvalidCharacterException, SubEquationException;
}
