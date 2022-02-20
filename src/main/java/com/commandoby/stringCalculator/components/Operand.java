package com.commandoby.stringCalculator.components;

import com.commandoby.stringCalculator.enums.Operation;

public class Operand {
	Operation operation;
	int operandNumber;
	
	public Operand(Operation operation, int operandNumber) {
		super();
		this.operation = operation;
		this.operandNumber = operandNumber;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public int getOperandNumber() {
		return operandNumber;
	}

	public void setOperandNumber(int operandNumber) {
		this.operandNumber = operandNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + operandNumber;
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operand other = (Operand) obj;
		if (operandNumber != other.operandNumber)
			return false;
		if (operation != other.operation)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Operand [operation=" + operation + ", operandNumber=" + operandNumber + "]";
	}
	
	
}
