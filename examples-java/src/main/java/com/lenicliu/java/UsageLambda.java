package com.lenicliu.java;

import java.io.File;
import java.util.Arrays;

/**
 * Java8 Lambda Expressions Examples
 * 
 * @author lenicliu
 *
 */
public class UsageLambda {

	public static void main(String[] args) {

		new Thread(() -> System.out.println(Thread.currentThread().getName())).start();

		new Thread(() -> new Thread(() -> new Thread(() -> System.out.println("x")).start()).start()).start();

		new Thread(() -> System.out.println(Arrays.asList(new File(".").listFiles(f -> {
			System.out.println(f.getName());
			return f.isFile();
		})))).start();
		;
	}
}