package com.commandoby.stringCalculator.service;

import java.util.List;

import com.commandoby.stringCalculator.components.Operand;

public interface Solver {
	double solve(List<Operand> operandList);
}
