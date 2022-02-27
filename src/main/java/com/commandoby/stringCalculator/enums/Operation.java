package com.commandoby.stringCalculator.enums;

public enum Operation {

	EXPONENTIETION {
		@Override
		public double action(double x, double y) {
			return Math.pow(x, y);
		}
	},
	MULTIPLY {
		@Override
		public double action(double x, double y) {
			return x * y;
		}
	},
	DIVIDE {
		@Override
		public double action(double x, double y) {
			return x / y;
		}
	},
	ADD {
		@Override
		public double action(double x, double y) {
			return x + y;
		}
	},
	SUBTRACT {
		@Override
		public double action(double x, double y) {
			return x - y;
		}
	};

	public abstract double action(double x, double y);
}
