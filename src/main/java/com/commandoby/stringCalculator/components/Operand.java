package com.commandoby.stringCalculator.components;

import java.util.LinkedList;
import java.util.Objects;

import com.commandoby.stringCalculator.enums.Operation;

public class Operand extends LinkedList<Operand> {
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
		int result = super.hashCode();
		result = prime * result + Objects.hash(operandNumber, operation);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operand other = (Operand) obj;
		return Double.doubleToLongBits(operandNumber) == Double.doubleToLongBits(other.operandNumber)
				&& operation == other.operation;
	}

	@Override
	public String toString() {
		return "Operand [operation=" + operation + ", operandNumber=" + operandNumber + ", getFirst()=" + getFirst()
				+ ", getLast()=" + getLast() + ", size()=" + size() + "]";
	}

	public Operand clone() {
		Operand operand = new Operand(null, 0/* , null */);

		if (this.operation != null) {
			operand.setOperation(this.operation);
		}
		if (super.size() > 0) {
			operand.addAll(this.subList(0, this.size()));
		} else {
			operand.setOperandNumber(this.operandNumber);
		}

		return operand;
	}

}
