package com.commandoby.stringCalculator;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.exceptions.SubEquationException;
import com.commandoby.stringCalculator.service.Reader;
import com.commandoby.stringCalculator.service.Solver;
import com.commandoby.stringCalculator.service.Writer;
import com.commandoby.stringCalculator.service.impl.ReaderImpl;
import com.commandoby.stringCalculator.service.impl.SolverImpl;
import com.commandoby.stringCalculator.service.impl.WriterImpl;

public class GetAnswerTests {
	private static Reader reader;
	private static Solver solver;
	private static Writer writer;

	@BeforeAll
	public static void setUp() {
		reader = new ReaderImpl();
		solver = new SolverImpl();
		writer = new WriterImpl();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/csv/GetAnswerTests.csv", numLinesToSkip = 1)
	public void getAnswer_Test(String input, BigDecimal expected)
			throws InvalidCharacterException, SubEquationException {
		BigDecimal actualAnswer = getAnswer(input);
		Assertions.assertEquals(expected, actualAnswer);
	}

	static BigDecimal getAnswer(String text)
			throws ArithmeticException, InvalidCharacterException, SubEquationException {
		return writer.writeOperandNumber(solver.solve(reader.read(text)));
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("All GetAnswer tests are finished!");
	}
}
