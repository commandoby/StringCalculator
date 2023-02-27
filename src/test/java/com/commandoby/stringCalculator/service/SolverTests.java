package com.commandoby.stringCalculator.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.service.impl.SolverImpl;

public class SolverTests {
	private static Solver solver;

	@BeforeAll
	public static void setUp() {
		solver = new SolverImpl();
	}

	@Test
	public void solver_Test1() {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(-1)));

		assertEquals(solver.solve(expectedOperand), -1);
	}

	@Test
	public void solver_Test2() {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.ADD, new BigDecimal(2)));

		assertEquals(solver.solve(expectedOperand), 4);
	}

	@Test
	public void solver_Test3() {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.FIRST_SUBTRACT, new BigDecimal(-2)));

		assertEquals(solver.solve(expectedOperand), 4);
	}

	@Test
	public void solver_Test4() {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.ADD, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.MULTIPLY, new BigDecimal(2)));

		assertEquals(solver.solve(expectedOperand), 6);
	}

	@Test
	public void solver_Test5() {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, null));
		expectedOperand.get(0).add(new Operand(null, new BigDecimal(2)));
		expectedOperand.get(0).add(new Operand(Operation.ADD, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.MULTIPLY, new BigDecimal(2)));

		assertEquals(solver.solve(expectedOperand), 8);
	}

	@Test
	public void solver_Test6() {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.FIRST_SUBTRACT, new BigDecimal(2)));

		assertEquals(solver.solve(expectedOperand), 0);
	}

	@Test
	public void solver_Test7() {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.DIVIDE, new BigDecimal(2)));

		assertEquals(solver.solve(expectedOperand), 1);
	}

	@Test
	public void solver_Test8() {
		Operand expectedOperand = new Operand(null, null);
		expectedOperand.add(new Operand(null, new BigDecimal(2)));
		expectedOperand.add(new Operand(Operation.EXPONENTIETION, new BigDecimal(3)));

		assertEquals(solver.solve(expectedOperand), 8);
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("All Solver tests are finished!");
	}
}
