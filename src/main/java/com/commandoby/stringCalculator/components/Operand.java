package com.commandoby.stringCalculator.components;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import com.commandoby.stringCalculator.enums.Operation;

public class Operand extends ArrayList<Operand> implements Serializable {
	private static final long serialVersionUID = 1L;
	Operation operation;
	BigDecimal operandNumber;

	public Operand(Operation operation, BigDecimal operandNumber) {
		this.operation = operation;
		this.operandNumber = operandNumber;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public BigDecimal getOperandNumber() {
		return operandNumber;
	}

	public void setOperandNumber(BigDecimal operandNumber) {
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
		return Objects.equals(operandNumber, other.operandNumber) && operation == other.operation;
	}

	@Override
	public String toString() {

		StringBuilder listToString = new StringBuilder();
		for (Operand o : this) {
			listToString.append("\n" + o.toString() + "");
		}
		return "Operand [operation=" + operation + ", operandNumber=" + operandNumber + ", size=" + size()
				+ listToString + " ]";
	}

	public Operand clone() {
		Operand operand = new Operand(null, null);

		if (this.operation != null) {
			operand.setOperation(this.operation);
		}
		if (super.size() > 0) {
			for (Operand o : this) {
				operand.add(o.clone());
			}
		} else {
			operand.setOperandNumber(this.operandNumber);
		}

		return operand;
	}

}
