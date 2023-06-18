package com.commandoby.stringCalculator.enums;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.commandoby.stringCalculator.service.impl.WriterImpl;

import ch.obermuhlner.math.big.BigDecimalMath;

public enum Operation {

	EXPONENTIETION("^", "\\^", 1, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return BigDecimalMath.pow(a, b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
		}
	},
	MULTIPLY(" * ", "\\*|x", 2, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return a.multiply(b).stripTrailingZeros();
		}
	},
	DIVIDE(" / ", "/", 2, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return a.divide(b, WriterImpl.scale + 10, RoundingMode.HALF_UP).stripTrailingZeros();
		}
	},
	ADD(" + ", "\\+", 3, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return a.add(b).stripTrailingZeros();
		}
	},
	FIRST_SUBTRACT(" - ", "-", 3, OperationType.FIRST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return a.subtract(b).stripTrailingZeros();
		}
	},
	SECOND_SUBTRACT("-", "-", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return b.multiply(new BigDecimal(-1));
		}
	},
	DEGREE("°", "°|deg", 0, OperationType.LAST) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return b.multiply(BigDecimalMath.pi(new MathContext(WriterImpl.scale + 20)))
					.divide(new BigDecimal(180), WriterImpl.scale + 20, RoundingMode.HALF_UP).stripTrailingZeros();
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
			return BigDecimalMath.sin(b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
		}
	},
	COS("cos", "cos", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return BigDecimalMath.cos(b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
		}
	},
	TG("tg", "tg|tan", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return BigDecimalMath.tan(b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
		}

	},
	CTG("ctg", "ctg|ctan", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return BigDecimalMath.cot(b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
		}

	},
	ASIN("arcsin", "arcsin|asin", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return BigDecimalMath.asin(b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
		}
	},
	ACOS("arccos", "arccos|acos", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return BigDecimalMath.acos(b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
		}
	},
	ATG("arctg", "arctg|atg", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return BigDecimalMath.atan(b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
		}

	},
	ACTG("arcctg", "arcctg|actg", 0, OperationType.SECOND) {
		@Override
		public BigDecimal action(BigDecimal a, BigDecimal b) {
			return BigDecimalMath.acot(b, new MathContext(WriterImpl.scale + 10)).stripTrailingZeros();
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
