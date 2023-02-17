package com.commandoby.stringCalculator.enums;

public enum Operation {

	EXPONENTIETION("^", "\\^", OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return Math.pow(x, y);
		}
	},
	MULTIPLY(" * ", "\\*", OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return x * y;
		}
	},
	DIVIDE(" / ", "/", OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return x / y;
		}
	},
	ADD(" + ", "\\+", OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return x + y;
		}
	},
	FIRST_SUBTRACT(" - ", "-", OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return x - y;
		}
	},
	SECOND_SUBTRACT("-", "-", OperationType.SECOND) {
		@Override
		public double action(double x, double y) {
			return y*-1;
		}
	},
	SIN("sin", "sin", OperationType.SECOND) {
		@Override
		public double action(double x, double y) {
			return Math.sin(y);
		}
	},
	COS("cos", "cos", OperationType.SECOND) {
		@Override
		public double action(double x, double y) {
			return Math.cos(y);
		}
	},
	DEGREE("°", "°", OperationType.LAST) {
		@Override
		public double action(double x, double y) {
			return y*Math.PI/180;
		}
	},
	PERCENT("%", "%", OperationType.LAST) {
		@Override
		public double action(double x, double y) {
			return y/100;
		}
	};

	private String text;
	private String pattern;
	private OperationType type;

	private Operation(String text, String pattern, OperationType type) {
		this.text = text;
		this.pattern = pattern;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public String getPattern() {
		return pattern;
	}

	public OperationType getType() {
		return type;
	}

	public abstract double action(double x, double y);
}
