package com.lenicliu.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Java8 Stream Operation Example
 * 
 * @author lenicliu
 *
 */
public class UsageStream {

	public static void main(String[] args) {
		List<Integer> ints = Arrays.asList(7, 1, 4, 3, 2, 5, 6, 9, 8);

		// all match
		System.out.println("all match");
		boolean allMatch = true;
		for (Integer i : ints) {
			allMatch &= i > 8;
		}
		System.out.println(allMatch);
		System.out.println(ints.stream().allMatch(i -> i > 8));
		System.out.println();

		// any match
		System.out.println("any match");
		boolean anyMatch = false;
		for (Integer i : ints) {
			anyMatch |= i > 8;
		}
		System.out.println(anyMatch);
		System.out.println(ints.stream().anyMatch(i -> i > 8));
		System.out.println();

		// none match
		System.out.println("none match");
		boolean noneMatch = true;
		for (Integer i : ints) {
			noneMatch |= i == 8;
		}
		System.out.println(noneMatch);
		System.out.println(ints.stream().noneMatch(i -> i == 8));
		System.out.println();

		// sum
		System.out.println("sum");
		int sum = 0;
		for (Integer i : ints) {
			sum += i;
		}
		System.out.println(sum);
		System.out.println(ints.stream().collect(Collectors.summingInt(i -> i)).doubleValue());
		System.out.println();

		// count
		System.out.println("count");
		System.out.println(ints.size());
		System.out.println(ints.stream().collect(Collectors.counting()).doubleValue());
		System.out.println();

		// avg
		System.out.println("avg");
		System.out.println(sum / ints.size());
		System.out.println(ints.stream().collect(Collectors.averagingInt(i -> i)).doubleValue());
		System.out.println();

		// mapping
		System.out.println("mapping");
		Map<Integer, Integer> mapping = new HashMap<>();
		for (Integer i : ints) {
			mapping.put(i, i * i);
		}
		System.out.println(mapping);
		System.out.println(ints.stream().collect(Collectors.toMap(i -> i, i -> i * i)));
		System.out.println();

		// reduction
		System.out.println("reduction");
		int product = 1;
		for (Integer i : ints) {
			product *= i;
		}
		System.out.println(product);
		System.out.println(ints.stream().collect(Collectors.reducing((i, j) -> i * j)).get());
		System.out.println();

		// remove duplicate value
		System.out.println("remove duplicate value");
		List<Integer> ints2 = Arrays.asList(1, 2, 3, 4, 5, 1, 3, 5, 7);
		System.out.println(new HashSet<>(ints2));
		System.out.println(ints2.stream().distinct().collect(Collectors.toList()));
		System.out.println();

		// find more than 5
		System.out.println("find more than 5");
		List<Integer> moreThan5 = new ArrayList<>();
		for (Integer i : ints) {
			if (i > 5) {
				moreThan5.add(i);
			}
		}
		System.out.println(moreThan5);
		System.out.println(ints.stream().filter(i -> i > 5).collect(Collectors.toList()));
		System.out.println();

		// find any
		System.out.println("find any");
		System.out.println(ints.stream().findAny().get());
		System.out.println(ints.stream().findAny().get());
		System.out.println(ints.stream().findAny().get());
		System.out.println();

		// map
		System.out.println("map");
		List<Integer> mapped = new ArrayList<>();
		for (Integer i : ints) {
			mapped.add(i * i);
		}
		System.out.println(mapped);
		System.out.println(ints.stream().map(i -> i * i).collect(Collectors.toList()));
		System.out.println();

		// reduce
		System.out.println("reduce");
		int reduce = 0;
		for (Integer i : ints) {
			reduce += i;
		}
		System.out.println(reduce);
		System.out.println(ints.stream().reduce((i, j) -> i + j).get());
		System.out.println();

		// sort
		System.out.println("sort");
		System.out.println(ints);
		List<Integer> sorted = new ArrayList<>(ints);
		Collections.sort(sorted);
		System.out.println(sorted);
		System.out.println(ints.stream().sorted().collect(Collectors.toList()));
		System.out.println();
	}
}