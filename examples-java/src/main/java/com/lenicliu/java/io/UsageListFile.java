package com.lenicliu.java.io;

import java.io.File;
import java.io.IOException;

public class UsageListFile {
	public static void main(String[] args) throws IOException {
		print(new File("."));
	}

	public static void print(File file) throws IOException {
		if (file.isDirectory()) {
			System.out.println(file.getCanonicalPath());
			File[] files = file.listFiles();
			if (files != null) {
				for (int i = 0, len = files.length; i < len; i++) {
					print(files[i]);
				}
			}
		} else {
			System.out.println(file.getCanonicalPath());
		}
	}
}