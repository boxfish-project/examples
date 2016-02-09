package com.lenicliu.java;

public class Perf {
	static int	_l1	= 1;
	static int	_l2	= 10;
	static int	_l3	= 100;
	static int	_l4	= 1000;

	static class Timing {
		public Timing(int length) {
			super();
			this.length = length;
		}

		int		length;
		long	insert	= 0;
		long	search	= 0;
		long	remove	= 0;

		@Override
		public String toString() {
			return String.format("%d\t\t%d\t\t%d\t\t%d", length, insert, remove, search);
		}
	}
}