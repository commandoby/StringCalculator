package com.commandoby.stringCalculator.service.impl;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.service.Solver;

public class SolverImpl implements Solver {

	@Override
	public double solve(Operand operand) {
		Solver solver = new SolverImpl();

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
		double operandNumberFirst = operand.getOperandList().get(operandNumber - 1).getOperandNumber();
		double operandNumberSecond = operand.getOperandList().get(operandNumber).getOperandNumber();
		double opernadNumberResult = operation.action(operandNumberFirst, operandNumberSecond);
		operand.getOperandList().get(operandNumber - 1).setOperandNumber(opernadNumberResult);
		operand.getOperandList().remove(operandNumber);
	}

}
