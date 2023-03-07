package com.commandoby.stringCalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.impl.WriterImpl;

public class WriterTests {
	private static Writer writer;

	@BeforeAll
	public static void setUp() {
		writer = new WriterImpl();
	}

	@Test
	public void writer_Test1() throws WriteException {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(-1)));

		assertEquals(writer.write(expectedOperand), "-1");
	}

	@Test
	public void writer_Test2() throws WriteException {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.ADD, new BigDecimal(2)));

		assertEquals(writer.write(expectedOperand), "2 + 2");
	}

	@Test
	public void writer_Test3() throws WriteException {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.FIRST_SUBTRACT, new BigDecimal(-2)));

		assertEquals(writer.write(expectedOperand), "2 - -2");
	}

	@Test
	public void writer_Test4() throws WriteException {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.ADD, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.MULTIPLY, new BigDecimal(2)));

		assertEquals(writer.write(expectedOperand), "2 + 2 * 2");
	}

	@Test
	public void writer_Test5() throws WriteException {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, null));
		expectedOperand.get(0).add(new Operand(null, new BigDecimal(2)));
		expectedOperand.get(0).add(new Operand(Operation.ADD, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.MULTIPLY, new BigDecimal(2)));

		assertEquals(writer.write(expectedOperand), "(2 + 2) * 2");
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("All Writer tests are finished!");
	}
}
