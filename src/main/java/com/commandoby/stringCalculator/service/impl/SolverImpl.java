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
import com.commandoby.stringCalculator.swing.ViewConsoleSwing;

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
		for (int i = 0; i < operationPriority.size(); i++) {
			for (int j = 1; j < operand.getOperandList().size(); j++) {
				for (int k = 0; k < operationPriority.get(i).size(); k++) {
					if (operand.getOperandList().get(j).getOperation().equals(operationPriority.get(i).get(k))) {
						solveOperand(operand, operationPriority.get(i).get(k), j);
						j = 0;
						break;
					}
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
		try {
			Operand operand = new Operand(null, 0, new ArrayList<Operand>());
			operand.getOperandList().add(operandFirst);
			operand.getOperandList().add(operandSecond);
			operand.getOperandList().get(0).setOperation(null);

			String detailedSolutionText = "[" + writer.write(operand) + " = " + writer.writeOperandNumber(result)
					+ "]  " + writer.write(staticOperand);
			if (Application.console) {
				System.out.println(detailedSolutionText);
			} else {
				ViewConsoleSwing.println(detailedSolutionText);
			}
		} catch (WriteException e) {
			log.error(e);
		}
	}

}
