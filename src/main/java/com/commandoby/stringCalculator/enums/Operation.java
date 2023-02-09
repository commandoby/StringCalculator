package com.commandoby.stringCalculator.enums;

public enum Operation {

	EXPONENTIETION("^") {
		@Override
		public double action(double x, double y) {
			return Math.pow(x, y);
		}
	},
	MULTIPLY(" * ") {
		@Override
		public double action(double x, double y) {
			return x * y;
		}
	},
	DIVIDE(" / ") {
		@Override
		public double action(double x, double y) {
			return x / y;
		}
	},
	ADD(" + ") {
		@Override
		public double action(double x, double y) {
			return x + y;
		}
	},
	SUBTRACT(" - ") {
		@Override
		public double action(double x, double y) {
			return x - y;
		}
	};

	private String text;

	private Operation(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public abstract double action(double x, double y);
}
