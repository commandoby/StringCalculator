package com.commandoby.stringCalculator.components;

import java.util.List;
import java.util.Objects;

import com.commandoby.stringCalculator.enums.Operation;

public class Operand {
	Operation operation;
	double operandNumber;
	List<Operand> operandList;

	public Operand(Operation operation, double operandNumber, List<Operand> operandList) {
		this.operation = operation;
		this.operandNumber = operandNumber;
		this.operandList = operandList;
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

	public List<Operand> getOperandList() {
		return operandList;
	}

	public void setOperandList(List<Operand> operandList) {
		this.operandList = operandList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(operandList, operandNumber, operation);
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
		return Objects.equals(operandList, other.operandList)
				&& Double.doubleToLongBits(operandNumber) == Double.doubleToLongBits(other.operandNumber)
				&& operation == other.operation;
	}

	@Override
	public String toString() {
		return "Operand [operation=" + operation + ", operandNumber=" + operandNumber + ", operandList=" + operandList
				+ "]";
	}

	public Operand clone() {
		Operand operand = new Operand(null, 0, null);

		if (this.operation != null) {
			operand.setOperation(this.operation);
		}
		if (this.operandList != null) {
			operand.setOperandList(this.operandList);
		} else {
			operand.setOperandNumber(this.operandNumber);
		}

		return operand;
	}

}
