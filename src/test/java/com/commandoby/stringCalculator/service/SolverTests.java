package com.commandoby.stringCalculator.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
		Operand expectedOperand = new Operand(null, 0, new ArrayList<Operand>());
		expectedOperand.getOperandList().add(new Operand(null, -1, null));

		assertEquals(solver.solve(expectedOperand), -1);
	}

	@Test
	public void solver_Test2() {
		Operand expectedOperand = new Operand(null, 0, new ArrayList<Operand>());
		expectedOperand.getOperandList().add(new Operand(null, 2, null));
		expectedOperand.getOperandList().add(new Operand(Operation.ADD, 2, null));

		assertEquals(solver.solve(expectedOperand), 4);
	}

	@Test
	public void solver_Test3() {
		Operand expectedOperand = new Operand(null, 0, new ArrayList<Operand>());
		expectedOperand.getOperandList().add(new Operand(null, 2, null));
		expectedOperand.getOperandList().add(new Operand(Operation.SUBTRACT, -2, null));

		assertEquals(solver.solve(expectedOperand), 4);
	}

	@Test
	public void solver_Test4() {
		Operand expectedOperand = new Operand(null, 0, new ArrayList<Operand>());
		expectedOperand.getOperandList().add(new Operand(null, 2, null));
		expectedOperand.getOperandList().add(new Operand(Operation.ADD, 2, null));
		expectedOperand.getOperandList().add(new Operand(Operation.MULTIPLY, 2, null));

		assertEquals(solver.solve(expectedOperand), 6);
	}

	@Test
	public void solver_Test5() {
		Operand expectedOperand = new Operand(null, 0, new ArrayList<Operand>());
		expectedOperand.getOperandList().add(new Operand(null, 0, new ArrayList<Operand>()));
		expectedOperand.getOperandList().get(0).getOperandList().add(new Operand(null, 2, null));
		expectedOperand.getOperandList().get(0).getOperandList().add(new Operand(Operation.ADD, 2, null));
		expectedOperand.getOperandList().add(new Operand(Operation.MULTIPLY, 2, null));

		assertEquals(solver.solve(expectedOperand), 8);
	}

	@Test
	public void solver_Test6() {
		Operand expectedOperand = new Operand(null, 0, new ArrayList<Operand>());
		expectedOperand.getOperandList().add(new Operand(null, 2, null));
		expectedOperand.getOperandList().add(new Operand(Operation.SUBTRACT, 2, null));

		assertEquals(solver.solve(expectedOperand), 0);
	}

	@Test
	public void solver_Test7() {
		Operand expectedOperand = new Operand(null, 0, new ArrayList<Operand>());
		expectedOperand.getOperandList().add(new Operand(null, 2, null));
		expectedOperand.getOperandList().add(new Operand(Operation.DIVIDE, 2, null));

		assertEquals(solver.solve(expectedOperand), 1);
	}

	@Test
	public void solver_Test8() {
		Operand expectedOperand = new Operand(null, 0, new ArrayList<Operand>());
		expectedOperand.getOperandList().add(new Operand(null, 2, null));
		expectedOperand.getOperandList().add(new Operand(Operation.EXPONENTIETION, 3, null));

		assertEquals(solver.solve(expectedOperand), 8);
	}

	@AfterAll
	public static void tearDown() {
		System.out.println("All Solver tests are finished!");
	}
}
