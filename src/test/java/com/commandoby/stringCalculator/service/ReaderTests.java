package com.commandoby.stringCalculator.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
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
	public void reader_Test1() throws InvalidCharacterException, SubEquationException {
		Operand expected = new Operand(null, null);
		expected.add(new Operand(Operation.SECOND_SUBTRACT, new BigDecimal(1)));

		Operand actual = reader.read("-1");
		assertEquals(expected, actual);
	}
	
	@Test
	public void reader_Test2() throws InvalidCharacterException, SubEquationException {
		Operand expected = new Operand(null, null);
		expected.add(new Operand(null, new BigDecimal(10)));
		
		Operand actual = reader.read("   10   ");
		assertEquals(expected, actual);
	}
	
	@Test
	public void reader_Test3() throws InvalidCharacterException, SubEquationException {
		Operand expected = new Operand(null, null);
		expected.add(new Operand(null, new BigDecimal(1)));
		expected.add(new Operand(Operation.ADD, new BigDecimal(2)));
		
		Operand actual = reader.read("1 + 2");
		assertEquals(expected, actual);
	}
	
	@Test
	public void reader_Test4() throws InvalidCharacterException, SubEquationException {
		Operand expected = new Operand(null, null);
		expected.add(new Operand(null, new BigDecimal(2)));
		expected.add(new Operand(Operation.FIRST_SUBTRACT, new BigDecimal(2)));
		expected.add(new Operand(Operation.MULTIPLY, new BigDecimal(2)));
		
		Operand actual = reader.read("2-2*2");
		assertEquals(expected, actual);
	}
	
	@Test
	public void reader_Test5() throws InvalidCharacterException, SubEquationException {
		Operand expected = new Operand(null, null);
		expected.add(new Operand(null, null));
		expected.get(0).add(new Operand(null, new BigDecimal(2)));
		expected.get(0).add(new Operand(Operation.ADD, new BigDecimal(2)));
		expected.add(new Operand(Operation.DIVIDE, new BigDecimal(2)));
		
		Operand actual = reader.read("(2+2)/2");
		assertEquals(expected, actual);
	}
	
	@Test
	public void reader_Test6() throws InvalidCharacterException, SubEquationException {
		Operand expected = new Operand(null, null);
		expected.add(new Operand(null, null));
		expected.get(0).add(new Operand(null, new BigDecimal(2)));
		expected.get(0).add(new Operand(Operation.DIVIDE, null));
		expected.get(0).get(1).add(new Operand(null, new BigDecimal(2)));
		expected.get(0).get(1).add(new Operand(Operation.ADD, new BigDecimal(2)));
		expected.add(new Operand(Operation.EXPONENTIETION, new BigDecimal(2)));
		
		Operand actual = reader.read("(2/(2+2))^2");
		assertEquals(expected, actual);
	}
	
	@Test
	public void reader_Test7() throws InvalidCharacterException, SubEquationException {
		Operand expected = new Operand(null, null);
		expected.add(new Operand(null, new BigDecimal(2)));
		expected.add(new Operand(Operation.ADD, null));
		expected.get(1).add(new Operand(Operation.SECOND_SUBTRACT, null));
		expected.get(1).get(0).add(new Operand(Operation.COS, null));
		expected.get(1).get(0).get(0).add(new Operand(Operation.DEGREE, new BigDecimal(20)));
		
		Operand actual = reader.read("2+-cos20°");
		assertEquals(expected, actual);
	}
	
	@Test
	public void invalidCharacterException_Test() {
		Throwable exception = assertThrows(InvalidCharacterException.class,
				() -> reader.read("2+b"));
		assertEquals("Invalid characters: +b", exception.getMessage());
	}
	
	@Test
	public void subEquationException_Test1() {
		Throwable exception = assertThrows(SubEquationException.class,
				() -> reader.read("2+(2*2"));
		assertEquals("Missing closing bracket.", exception.getMessage());
	}
	
	@Test
	public void subEquationException_Test2() {
		Throwable exception = assertThrows(SubEquationException.class,
				() -> reader.read("2+(2*2))"));
		assertEquals("Missing opening bracket.", exception.getMessage());
	}

    @AfterAll
    public static void tearDown() {
        System.out.println("All Reader tests are finished!");
    }
}
