package com.commandoby.stringCalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class GetAnswerTests {

	@ParameterizedTest
	@CsvFileSource(resources = "/csv/GetAnswerTests.csv", numLinesToSkip = 1)
	public void getAnswer_Test(String input, double expected) {
		double actualAnswer = Application.getAnswer(input);
		Assertions.assertEquals(expected, actualAnswer);
	}
}
