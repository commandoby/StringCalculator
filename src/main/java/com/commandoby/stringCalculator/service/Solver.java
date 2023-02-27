package com.commandoby.stringCalculator.service;

import java.math.BigDecimal;

import com.commandoby.stringCalculator.components.Operand;

public interface Solver {
	BigDecimal solve(Operand operand);
}
