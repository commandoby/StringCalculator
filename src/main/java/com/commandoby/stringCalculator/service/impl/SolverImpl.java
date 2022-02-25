package com.commandoby.stringCalculator.service.impl;

import java.util.List;

import com.commandoby.stringCalculator.components.Operand;
import com.commandoby.stringCalculator.enums.Operation;
import com.commandoby.stringCalculator.service.Solver;

public class SolverImpl implements Solver {
	private static final Operation[] operationPriorityList = {
			Operation.EXPONENTIETION, Operation.MULTIPLY, Operation.DIVIDE,
			Operation.ADD, Operation.SUBTRACT};

	@Override
	public double solve(List<Operand> operandList) {
		/*while (operandList.size() > 1) {
			
		}*/
		
		return operandList.get(0).getOperandNumber();
	}

}
