package com.commandoby.stringCalculator.service.impl;

import org.apache.log4j.Logger;

import com.commandoby.stringCalculator.Application;
import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.enums.OperationType;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Solver;
import com.commandoby.stringCalculator.service.Writer;

public class SolverImpl implements Solver {
	private Writer writer = new WriterImpl();
	private Logger log = Logger.getLogger(getClass());

	public static boolean detailedSolution = false;
	private static Operand staticOperand = null;

	@Override
	public double solve(Operand operand) {
		Solver solver = new SolverImpl();

		if (staticOperand == null || staticOperand.isEmpty()) {
			staticOperand = operand;
		}

		if (!operand.isEmpty()) {
			for (int i = 0; i < operand.size(); i++) {
				if (operand.get(i).size() > 0) {
					operand.get(i).setOperandNumber(solver.solve(operand.get(i)));
					operand.get(i).clear();
				}
			}

			while (operand.size() > 1 || operand.get(0).getOperation() != null) {
				solverLoop(operand);
			}

			operand.setOperandNumber(operand.get(0).getOperandNumber());
			operand.clear();
		} else {
			return operand.getOperandNumber();
		}

		return operand.getOperandNumber();
	}

	private void solverLoop(Operand operand) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < operand.size(); j++) {
				for (Operation operation : Operation.values()) {
					if (operand.get(j).getOperation() != null && operand.get(j).getOperation().equals(operation)
							&& operand.get(j).getOperation().getPriority() == i) {
						solveOperand(operand, j);
						j = 0;
						break;
					}
				}
			}
		}
	}

	private void solveOperand(Operand operand, int operandNumber) {
		Operand operandSecond = operand.get(operandNumber).clone();
		if (operandNumber > 0 && operand.get(operandNumber).getOperation().getType() == OperationType.FIRST) {
			Operand operandFirst = operand.get(operandNumber - 1).clone();
			double opernadNumberResult = operand.get(operandNumber).getOperation()
					.action(operandFirst.getOperandNumber(), operandSecond.getOperandNumber());
			operand.get(operandNumber - 1).setOperandNumber(opernadNumberResult);
			operand.remove(operandNumber);

			if (detailedSolution) {
				descriptionSolution(operandFirst, operandSecond, opernadNumberResult);
			}
		} else {
			double opernadNumberResult = operand.get(operandNumber).getOperation()
					.action(0, operand.get(operandNumber).getOperandNumber());
			operand.get(operandNumber).setOperandNumber(opernadNumberResult);
			operand.get(operandNumber).setOperation(null);
			
			if (detailedSolution) {
				descriptionSolution(null, operandSecond, opernadNumberResult);
			}
		}
	}

	private void descriptionSolution(Operand operandFirst, Operand operandSecond, double result) {
		try {
			Operand operand = new Operand(null, 0);
			if (operandFirst != null) {
				operand.add(operandFirst);
				operand.get(0).setOperation(null);
			}
			operand.add(operandSecond);

			String detailedSolutionText = "[" + writer.write(operand) + " = " + writer.writeOperandNumber(result)
					+ "]  " + writer.write(staticOperand);
			Application.print(detailedSolutionText + "\n");
		} catch (WriteException e) {
			log.error(e);
		}
	}

}
