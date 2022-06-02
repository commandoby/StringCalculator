package com.commandoby.stringCalculator.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.commandoby.stringCalculator.Application;
import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.exceptions.WriteException;
import com.commandoby.stringCalculator.service.Solver;
import com.commandoby.stringCalculator.service.Writer;

public class SolverImpl implements Solver {
	private Writer writer = new WriterImpl();
	private Logger log = Logger.getLogger(getClass());

	public static boolean detailedSolution = false;
	private static Operand staticOperand = null;

	private List<List<Operation>> operationPriority = new ArrayList<>();

	{
		while (operationPriority.size() < 3) {
			operationPriority.add(new ArrayList<Operation>());
		}
		operationPriority.get(0).add(Operation.EXPONENTIETION);
		operationPriority.get(1).add(Operation.MULTIPLY);
		operationPriority.get(1).add(Operation.DIVIDE);
		operationPriority.get(2).add(Operation.ADD);
		operationPriority.get(2).add(Operation.SUBTRACT);
	}

	@Override
	public double solve(Operand operand) {
		Solver solver = new SolverImpl();

		if (staticOperand == null || staticOperand.size() == 0) {
			staticOperand = operand;
		}

		if (operand.size() > 0) {
			for (int i = 0; i < operand.size(); i++) {
				if (operand.get(i).size() > 0) {
					operand.get(i).setOperandNumber(solver.solve(operand.get(i)));
					operand.get(i).clear();
					;
				}
			}

			while (operand.size() > 1) {
				solverLoop(operand);
			}

			operand.setOperandNumber(operand.get(0).getOperandNumber());
			operand.clear();
			;
		} else {
			return operand.getOperandNumber();
		}

		return operand.getOperandNumber();
	}

	private void solverLoop(Operand operand) {
		for (int i = 0; i < operationPriority.size(); i++) {
			for (int j = 1; j < operand.size(); j++) {
				for (int k = 0; k < operationPriority.get(i).size(); k++) {
					if (operand.get(j).getOperation().equals(operationPriority.get(i).get(k))) {
						solveOperand(operand, operationPriority.get(i).get(k), j);
						j = 0;
						break;
					}
				}
			}
		}
	}

	private void solveOperand(Operand operand, Operation operation, int operandNumber) {
		Operand operandFirst = operand.get(operandNumber - 1).clone();
		Operand operandSecond = operand.get(operandNumber).clone();
		double opernadNumberResult = operation.action(operandFirst.getOperandNumber(),
				operandSecond.getOperandNumber());
		operand.get(operandNumber - 1).setOperandNumber(opernadNumberResult);
		operand.remove(operandNumber);

		if (detailedSolution) {
			descriptionSolution(operandFirst, operandSecond, opernadNumberResult);
		}
	}

	private void descriptionSolution(Operand operandFirst, Operand operandSecond, double result) {
		try {
			Operand operand = new Operand(null, 0);
			operand.add(operandFirst);
			operand.add(operandSecond);
			operand.get(0).setOperation(null);

			String detailedSolutionText = "[" + writer.write(operand) + " = " + writer.writeOperandNumber(result)
					+ "]  " + writer.write(staticOperand);
			Application.print(detailedSolutionText + "\n");
		} catch (WriteException e) {
			log.error(e);
		}
	}

}
