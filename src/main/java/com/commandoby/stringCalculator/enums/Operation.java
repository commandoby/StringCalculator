package com.commandoby.stringCalculator.enums;

public enum Operation {

	EXPONENTIETION("^", "\\^", 1, OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return Math.pow(x, y);
		}
	},
	MULTIPLY(" * ", "\\*", 2, OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return x * y;
		}
	},
	DIVIDE(" / ", "/", 2, OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return x / y;
		}
	},
	ADD(" + ", "\\+", 3, OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return x + y;
		}
	},
	FIRST_SUBTRACT(" - ", "-", 3, OperationType.FIRST) {
		@Override
		public double action(double x, double y) {
			return x - y;
		}
	},
	SECOND_SUBTRACT("-", "-", 0, OperationType.SECOND) {
		@Override
		public double action(double x, double y) {
			return y*-1;
		}
	},
	SIN("sin", "sin", 0, OperationType.SECOND) {
		@Override
		public double action(double x, double y) {
			return Math.sin(y);
		}
	},
	COS("cos", "cos", 0, OperationType.SECOND) {
		@Override
		public double action(double x, double y) {
			return Math.cos(y);
		}
	},
	DEGREE("°", "°", 0, OperationType.LAST) {
		@Override
		public double action(double x, double y) {
			return y*Math.PI/180;
		}
	},
	PERCENT("%", "%", 0, OperationType.LAST) {
		@Override
		public double action(double x, double y) {
			return y/100;
		}
	};

	private String text;
	private String pattern;
	private int priority;
	private OperationType type;

	private Operation(String text, String pattern, int priority, OperationType type) {
		this.text = text;
		this.pattern = pattern;
		this.priority = priority;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public String getPattern() {
		return pattern;
	}

	public int getPriority() {
		return priority;
	}

	public OperationType getType() {
		return type;
	}

	public abstract double action(double x, double y);
}
