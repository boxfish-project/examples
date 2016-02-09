package com.lenicliu.java.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

public class PerfCollection extends Perf {

	static Collection<Integer>	vector			= new Vector<>();
	static Collection<Integer>	stack			= new Stack<>();
	static Collection<Integer>	arrayList		= new ArrayList<>();
	static Collection<Integer>	linkedList		= new LinkedList<>();
	static Collection<Integer>	hashSet			= new HashSet<>();
	static Collection<Integer>	linkedHashSet	= new LinkedHashSet<>();
	static Collection<Integer>	treeSet			= new TreeSet<>();
	static Collection<Integer>	arrayDeque		= new ArrayDeque<>();

	public static void main(String[] args) {
		String columns = "length\t\tinsert\t\tremove\t\tsearch";
		System.out.println("Vector\n" + columns);
		System.out.println(performance(_l1, vector));
		System.out.println(performance(_l2, vector));
		System.out.println(performance(_l3, vector));
		System.out.println(performance(_l4, vector));

		System.out.println();
		System.out.println("Stack\n" + columns);
		System.out.println(performance(_l1, stack));
		System.out.println(performance(_l2, stack));
		System.out.println(performance(_l3, stack));
		System.out.println(performance(_l4, stack));

		System.out.println();
		System.out.println("ArrayList\n" + columns);
		System.out.println(performance(_l1, arrayList));
		System.out.println(performance(_l2, arrayList));
		System.out.println(performance(_l3, arrayList));
		System.out.println(performance(_l4, arrayList));

		System.out.println();
		System.out.println("LinkedList\n" + columns);
		System.out.println(performance(_l1, linkedList));
		System.out.println(performance(_l2, linkedList));
		System.out.println(performance(_l3, linkedList));
		System.out.println(performance(_l4, linkedList));

		System.out.println();
		System.out.println("HashSet\n" + columns);
		System.out.println(performance(_l1, hashSet));
		System.out.println(performance(_l2, hashSet));
		System.out.println(performance(_l3, hashSet));
		System.out.println(performance(_l4, hashSet));

		System.out.println();
		System.out.println("LinkedHashSet\n" + columns);
		System.out.println(performance(_l1, linkedHashSet));
		System.out.println(performance(_l2, linkedHashSet));
		System.out.println(performance(_l3, linkedHashSet));
		System.out.println(performance(_l4, linkedHashSet));

		System.out.println();
		System.out.println("TreeSet\n" + columns);
		System.out.println(performance(_l1, treeSet));
		System.out.println(performance(_l2, treeSet));
		System.out.println(performance(_l3, treeSet));
		System.out.println(performance(_l4, treeSet));

		System.out.println();
		System.out.println("ArrayDeque\n" + columns);
		System.out.println(performance(_l1, arrayDeque));
		System.out.println(performance(_l2, arrayDeque));
		System.out.println(performance(_l3, arrayDeque));
		System.out.println(performance(_l4, arrayDeque));
	}

	public static Timing performance(int length, Collection<Integer> collection) {
		Random r = new Random();
		long time = 0;

		Timing timing = new Timing(length);

		time = System.nanoTime();
		for (int i = 0; i < length; i++) {
			collection.add(r.nextInt());
		}
		timing.insert = System.nanoTime() - time;

		time = System.nanoTime();
		for (int i = 0; i < length; i++) {
			collection.contains(r.nextInt());
		}
		timing.search = System.nanoTime() - time;

		time = System.nanoTime();
		for (int i = 0; i < length; i++) {
			collection.remove(r.nextInt());
		}
		timing.remove = System.nanoTime() - time;
		return timing;
	}
}