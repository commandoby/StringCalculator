package com.commandoby.stringCalculator.components;

import com.commandoby.stringCalculator.enums.Operation;

public class Operand {
	Operation operation;
	double operandNumber;

	public Operand(Operation operation, double operandNumber) {
		this.operation = operation;
		this.operandNumber = operandNumber;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public double getOperandNumber() {
		return operandNumber;
	}

	public void setOperandNumber(double operandNumber) {
		this.operandNumber = operandNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(operandNumber);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (Double.doubleToLongBits(operandNumber) != Double.doubleToLongBits(other.operandNumber))
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
