package com.lenicliu.tutorials.core.collection.list;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class Usage {

	public static void main(String[] args) {
		arrayList();
		linkedList();
		vector();
		stack();
	}

	private static void arrayList() {
		System.out.println("java.util.ArrayList.ArrayList<Integer>()");
		ArrayList<Integer> list = new ArrayList<Integer>();
		list(list);
	}

	private static void linkedList() {
		System.out.println("java.util.LinkedList.LinkedList<Integer>()");
		LinkedList<Integer> list = new LinkedList<Integer>();
		list(list);
	}

	private static void vector() {
		System.out.println("java.util.Vector.Vector<Integer>()");
		Vector<Integer> list = new Vector<Integer>();
		list(list);
		Integer firstEl = list.firstElement();
		System.out.println("first element of vector: " + firstEl);
		Integer lastEl = list.lastElement();
		System.out.println("last element of vector: " + lastEl);
		Enumeration<Integer> elements = list.elements();
		while (elements.hasMoreElements()) {
			Integer el = elements.nextElement();
			System.out.println("enumeration vector: " + el);
		}
	}

	private static void stack() {
		System.out.println("java.util.Stack.Stack<Integer>()");
		Stack<Integer> list = new Stack<Integer>();
		list(list);

		Integer firstEl = list.firstElement();
		System.out.println("first element of stack: " + firstEl);
		Integer lastEl = list.lastElement();
		System.out.println("last element of stack: " + lastEl);
		Enumeration<Integer> elements = list.elements();
		while (elements.hasMoreElements()) {
			Integer el = elements.nextElement();
			System.out.println("enumeration stack: " + el);
		}

		Integer pop = list.pop();
		System.out.println("pop value of stack: " + pop);
		Integer peek = list.peek();
		System.out.println("peek value of stack: " + peek);
		Integer push = list.push(9999);
		System.out.println("push value of stack: " + push);
	}

	private static void list(List<Integer> list) {
		list.add(1);
		list.add(2);
		list.add(4);
		list.add(5);
		list.add(7);
		StringBuffer values = new StringBuffer();
		for (Integer value : list) {
			values.append(",").append(value);
		}
		System.out.println("values of list: " + values.substring(1));
		System.out.println("size of list: " + list.size());
		System.out.println("list is empty: " + list.isEmpty());
		System.out.println("list contains 3: " + list.contains(3));
		System.out.println("list contains 4: " + list.contains(4));
		Integer value = list.get(1);
		System.out.println("value of index 1 is : " + value);
		Integer removed = list.remove(3);
		System.out.println("value removed from list: " + removed);
		System.out.println("size of list after removed: " + list.size());
		Integer index = list.indexOf(7);
		System.out.println("index of value 7 is : " + index);
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			Integer i = it.next();
			System.out.println("traversing list: " + i);
		}
		System.out.println();
	}
}