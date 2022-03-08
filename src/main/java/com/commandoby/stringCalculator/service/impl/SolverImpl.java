package com.commandoby.stringCalculator.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Solver;
import com.commandoby.stringCalculator.service.Writer;

public class SolverImpl implements Solver {
	public static boolean detailedSolution = false;
	private static Operand staticOperand = null;

	@Override
	public double solve(Operand operand) {
		Solver solver = new SolverImpl();

		if (staticOperand == null || staticOperand.getOperandList() == null) {
			staticOperand = operand;
		}

		if (operand.getOperandList() != null) {
			for (int i = 0; i < operand.getOperandList().size(); i++) {
				if (operand.getOperandList().get(i).getOperandList() != null) {
					operand.getOperandList().get(i).setOperandNumber(solver.solve(operand.getOperandList().get(i)));
					operand.getOperandList().get(i).setOperandList(null);
				}
			}

			while (operand.getOperandList().size() > 1) {
				solverLoop(operand);
			}

			operand.setOperandNumber(operand.getOperandList().get(0).getOperandNumber());
			operand.setOperandList(null);
		} else {
			return operand.getOperandNumber();
		}

		return operand.getOperandNumber();
	}

	private void solverLoop(Operand operand) {
		for (int i = 0; i < Operation.values().length; i++) {
			for (int j = 1; j < operand.getOperandList().size(); j++) {
				if (operand.getOperandList().get(j).getOperation().equals(Operation.values()[i])) {
					solveOperand(operand, Operation.values()[i], j);
					j = 1;
				}
			}
		}
	}

	private void solveOperand(Operand operand, Operation operation, int operandNumber) {
		Operand operandFirst = operand.getOperandList().get(operandNumber - 1).clone();
		Operand operandSecond = operand.getOperandList().get(operandNumber).clone();
		double opernadNumberResult = operation.action(operandFirst.getOperandNumber(),
				operandSecond.getOperandNumber());
		operand.getOperandList().get(operandNumber - 1).setOperandNumber(opernadNumberResult);
		operand.getOperandList().remove(operandNumber);

		if (detailedSolution) {
			descriptionSolution(operandFirst, operandSecond, opernadNumberResult);
		}
	}

	private void descriptionSolution(Operand operandFirst, Operand operandSecond, double result) {
		Writer writer = new WriterImpl();
		Logger log = Logger.getLogger(getClass());

		try {
			Operand operand = new Operand(null, 0, new ArrayList<Operand>());
			operand.getOperandList().add(operandFirst);
			operand.getOperandList().add(operandSecond);
			operand.getOperandList().get(0).setOperation(null);

			System.out.println(writer.write(staticOperand) + "  [" + writer.write(operand) + " = "
					+ writer.writeOperandNumber(result) + "]");
		} catch (WriteException e) {
			log.error(e);
		}
	}

}
