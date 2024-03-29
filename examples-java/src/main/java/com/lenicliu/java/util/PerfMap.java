package com.lenicliu.java.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class PerfMap extends Perf {

	static Map<Integer, Integer>	hashMap			= new HashMap<>();
	static Map<Integer, Integer>	linkedHashMap	= new LinkedHashMap<>();
	static Map<Integer, Integer>	treeMap			= new TreeMap<>();

	public static void main(String[] args) {
		String columns = "length\t\tinsert\t\tremove\t\tsearch";
		System.out.println("HashMap\n" + columns);
		System.out.println(performance(_l1, hashMap));
		System.out.println(performance(_l2, hashMap));
		System.out.println(performance(_l3, hashMap));
		System.out.println(performance(_l4, hashMap));

		System.out.println();
		System.out.println("LinkedHashMap\n" + columns);
		System.out.println(performance(_l1, linkedHashMap));
		System.out.println(performance(_l2, linkedHashMap));
		System.out.println(performance(_l3, linkedHashMap));
		System.out.println(performance(_l4, linkedHashMap));

		System.out.println();
		System.out.println("TreeMap\n" + columns);
		System.out.println(performance(_l1, treeMap));
		System.out.println(performance(_l2, treeMap));
		System.out.println(performance(_l3, treeMap));
		System.out.println(performance(_l4, treeMap));
	}

	public static Timing performance(int length, Map<Integer, Integer> map) {
		Random r = new Random();
		long time = 0;

		Timing timing = new Timing(length);

		time = System.nanoTime();
		for (int i = 0; i < length; i++) {
			map.put(r.nextInt(), r.nextInt());
		}
		timing.insert = System.nanoTime() - time;

		time = System.nanoTime();
		for (int i = 0; i < length; i++) {
			map.get(r.nextInt());
		}
		timing.search = System.nanoTime() - time;

		time = System.nanoTime();
		for (int i = 0; i < length; i++) {
			map.remove(r.nextInt());
		}
		timing.remove = System.nanoTime() - time;
		return timing;
	}
}