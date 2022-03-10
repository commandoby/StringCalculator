package com.commandoby.stringCalculator.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.exceptions.ConflictOfOperationsException;
import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.exceptions.SubEquationException;
import com.commandoby.stringCalculator.service.impl.ReaderImpl;

public class ReaderTests {
	private static Reader reader;

	@BeforeAll
	public static void setUp() {
		reader = new ReaderImpl();
	}

	@Test
	public void Reader_Test1() throws InvalidCharacterException, ConflictOfOperationsException, SubEquationException {
		List<Operand> expected = new ArrayList<>();
		expected.add(new Operand(null, -1, null));

		List<Operand> actual = reader.read("-1");
		Assertions.assertEquals(expected, actual);
	}
}
