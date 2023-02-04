package com.commandoby.stringCalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.impl.ReaderImpl;
import com.commandoby.stringCalculator.service.impl.WriterImpl;

public class WriterTests {
	private static Writer writer;
	private static Reader reader;

	@BeforeAll
	public static void setUp() {
		writer = new WriterImpl();
		reader = new ReaderImpl();
	}

	@Test
	public void writer_Test1() throws WriteException {
		Operand expectedOperand = new Operand(null, 0);
		expectedOperand.add(new Operand(null, -1));

		assertEquals(writer.write(expectedOperand), "-1");
	}

	@Test
	public void writer_Test2() throws WriteException {
		Operand expectedOperand = new Operand(null, 0);
		expectedOperand.add(new Operand(null, 2));
		expectedOperand.add(new Operand(Operation.ADD, 2));

		assertEquals(writer.write(expectedOperand), "2 + 2");
	}

	@Test
	public void writer_Test3() throws WriteException {
		Operand expectedOperand = new Operand(null, 0);
		expectedOperand.add(new Operand(null, 2));
		expectedOperand.add(new Operand(Operation.SUBTRACT, -2));

		assertEquals(writer.write(expectedOperand), "2 - -2");
	}

	@Test
	public void writer_Test4() throws WriteException {
		Operand expectedOperand = new Operand(null, 0);
		expectedOperand.add(new Operand(null, 2));
		expectedOperand.add(new Operand(Operation.ADD, 2));
		expectedOperand.add(new Operand(Operation.MULTIPLY, 2));

		assertEquals(writer.write(expectedOperand), "2 + 2 * 2");
	}

	@Test
	public void writer_Test5() throws WriteException {
		Operand expectedOperand = new Operand(null, 0);
		expectedOperand.add(new Operand(null, 0));
		expectedOperand.get(0).add(new Operand(null, 2));
		expectedOperand.get(0).add(new Operand(Operation.ADD, 2));
		expectedOperand.add(new Operand(Operation.MULTIPLY, 2));

		assertEquals(writer.write(expectedOperand), "(2 + 2) * 2");
	}

	@Test
	public void writer_Test6() throws WriteException {
		Operand expectedOperand = new Operand(null, 0);
		expectedOperand.add(new Operand(null, 2));
		expectedOperand.add(new Operand(Operation.SUBTRACT, 2));

		assertEquals(writer.write(expectedOperand), "2 - 2");
	}

	@Test
	public void writer_Test7() throws WriteException {
		Operand expectedOperand = new Operand(null, 0);
		expectedOperand.add(new Operand(null, 2));
		expectedOperand.add(new Operand(Operation.DIVIDE, 2));

		assertEquals(writer.write(expectedOperand), "2 / 2");
	}

	@Test
	public void writer_Test8() throws WriteException {
		Operand expectedOperand = new Operand(null, 0);
		expectedOperand.add(new Operand(null, 2));
		expectedOperand.add(new Operand(Operation.EXPONENTIETION, 3));

		assertEquals(writer.write(expectedOperand), "2^3");
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("All Writer tests are finished!");
	}
}
