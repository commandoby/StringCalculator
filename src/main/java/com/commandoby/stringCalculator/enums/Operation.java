package com.commandoby.stringCalculator.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.commandoby.stringCalculator.service.impl.WriterImpl;

public enum Operation {

	EXPONENTIETION("^", "\\^", 1, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.pow(a.doubleValue(), b.doubleValue()));
		}
	},
	MULTIPLY(" * ", "\\*", 2, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return a.multiply(b);
		}
	},
	DIVIDE(" / ", "/", 2, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return a.divide(b, WriterImpl.numbersAfterTheDecimalPoint + 10, RoundingMode.HALF_UP);
		}
	},
	ADD(" + ", "\\+", 3, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return a.add(b);
		}
	},
	FIRST_SUBTRACT(" - ", "-", 3, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return a.subtract(b);
		}
	},
	SECOND_SUBTRACT("-", "-", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return b.multiply(new BigDecimal(-1));
		}
	},
	DEGREE("°", "°", 0, OperationType.LAST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return b.multiply(new BigDecimal(Math.PI)).divide(new BigDecimal(180),
					WriterImpl.numbersAfterTheDecimalPoint + 10, RoundingMode.HALF_UP);
		}
	},
	PERCENT("%", "%", 0, OperationType.LAST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return b.divide(new BigDecimal(100));
		}
	},
	SIN("sin", "sin", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.sin(b.doubleValue()));
		}
	},
	COS("cos", "cos", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.cos(b.doubleValue()));
		}
	},
	TG("tg", "tg|tan", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.tan(b.doubleValue()));
		}

	},
	CTG("ctg", "ctg|ctan", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.cos(b.doubleValue()) / Math.sin(b.doubleValue()));
		}

	},
	ASIN("arcsin", "arcsin", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.asin(b.doubleValue()));
		}
	},
	ACOS("arccos", "arccos", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.acos(b.doubleValue()));
		}
	},
	ATG("arctg", "arctg", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.atan(b.doubleValue()));
		}

	},
	ACTG("arcctg", "arcctg", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return new BigDecimal(Math.atan(1 / b.doubleValue()));
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

	public abstract BigDecimal action(BigDecimal a, BigDecimal b);
}
