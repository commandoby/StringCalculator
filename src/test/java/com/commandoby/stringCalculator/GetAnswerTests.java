package com.commandoby.stringCalculator;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.commandoby.stringCalculator.exceptions.InvalidCharacterException;
import com.commandoby.stringCalculator.exceptions.SubEquationException;

public class GetAnswerTests {

	@ParameterizedTest
	@CsvFileSource(resources = "/csv/GetAnswerTests.csv", numLinesToSkip = 1)
	public void getAnswer_Test(String input, BigDecimal expected) throws InvalidCharacterException, SubEquationException {
		BigDecimal actualAnswer = Application.getAnswer(input);
		Assertions.assertEquals(expected, actualAnswer);
	}

    @AfterAll
    public static void tearDown() {
        System.out.println("All GetAnswer tests are finished!");
    }
}
