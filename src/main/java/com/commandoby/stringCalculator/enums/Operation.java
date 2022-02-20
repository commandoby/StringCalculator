package com.commandoby.stringCalculator.enums;

public enum Operation {
	ADD{
		@Override
		public int action(int x, int y) {
			return x + y;
		}
    },
    SUBTRACT{
		@Override
		public int action(int x, int y) {
			return x-y;
		}
    },
    MULTIPLY{
		@Override
		public int action(int x, int y) {
			return x * y;
		}
    },
    DIVIDE{
		@Override
		public int action(int x, int y) {
			return x / y;
		}
    };
	
	public abstract int action(int x, int y);
}
