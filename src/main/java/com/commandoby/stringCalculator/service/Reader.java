package com.commandoby.stringCalculator.service;

import java.util.List;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.exceptions.ConflictOfOperationsException;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;

public interface Reader {
	List<Operand> read(String text) throws InvalidCharacterException, ConflictOfOperationsException;
}
