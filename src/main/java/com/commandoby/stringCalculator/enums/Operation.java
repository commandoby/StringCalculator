package com.commandoby.stringCalculator.enums;

public enum Operation {
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
	EXPONENTIETION {
		@Override
		public double action(double x, double y) {
			return Math.pow(x, y);
		}
	};

	public abstract double action(double x, double y);
}
