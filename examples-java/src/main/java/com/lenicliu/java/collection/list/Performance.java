package com.lenicliu.java.collection.list;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class Performance {

	public static void main(String[] args) throws Exception {
		final int size = 10000;
		StringBuffer csv = new StringBuffer();
		csv.append(String.format("%s,%s,%s,%s,%s\n", "--", "Round", "Add", "Search", "Remove"));
		for (int i = 1, n = 11; i < n; i++) {
			compare(csv, i, "ArrayList", new ArrayList<Integer>(), size);
			compare(csv, i, "ArrayList(Fix)", new ArrayList<Integer>(size), size);
			compare(csv, i, "LinkedList", new LinkedList<Integer>(), size);
			compare(csv, i, "Vector", new Vector<Integer>(), size);
			compare(csv, i, "Vector(Fix)", new Vector<Integer>(size), size);
			compare(csv, i, "Stack", new Stack<Integer>(), size);
			csv.append(",,,,\n");
		}
		FileWriter writer = new FileWriter("list.csv");
		writer.write(csv.toString());
		writer.close();
	}

	public static void compare(StringBuffer csv, int round, String description, List<Integer> list, int size) {
		long begin = 0, ending = 0;
		// add
		begin = System.nanoTime();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		ending = System.nanoTime();

		long t1 = ending - begin;

		// search
		begin = System.nanoTime();
		for (int i = size - 1; i >= 0; i--) {
			list.get(i);
		}
		ending = System.nanoTime();
		long t2 = ending - begin;

		// remove
		begin = System.nanoTime();
		for (int i = size - 1; i >= 0; i--) {
			list.remove(i);
		}
		ending = System.nanoTime();
		long t3 = ending - begin;
		csv.append(String.format("%s,%d,%d,%d,%d\n", description, round, t1, t2, t3));
	}
}