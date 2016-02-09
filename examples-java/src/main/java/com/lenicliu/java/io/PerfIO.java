package com.lenicliu.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PerfIO {

	public static void main(String[] args) throws IOException {
		System.out.println("Char\t" + copy0());
		System.out.println("Byte\t" + copy1());
		System.out.println("NIO\t" + copy2());
	}

	private static long copy0() throws IOException {
		FileReader reader = new FileReader("src.txt");
		FileWriter writer = new FileWriter("b0.txt");
		char[] buf = new char[1024];
		int read = -1;
		long begin = System.nanoTime();
		while ((read = reader.read(buf)) != -1) {
			writer.write(buf, 0, read);
		}
		long end = System.nanoTime();
		writer.close();
		reader.close();
		return end - begin;
	}

	private static long copy1() throws IOException {
		FileInputStream in = new FileInputStream("src.txt");
		FileOutputStream ot = new FileOutputStream("b1.txt");
		byte[] buf = new byte[1024];
		int read = -1;
		long begin = System.nanoTime();
		while ((read = in.read(buf)) != -1) {
			ot.write(buf, 0, read);
		}
		long end = System.nanoTime();
		ot.close();
		in.close();
		return end - begin;
	}

	private static long copy2() throws IOException {
		FileInputStream in = new FileInputStream("src.txt");
		FileOutputStream ot = new FileOutputStream("b2.txt");
		long begin = System.nanoTime();
		ot.getChannel().transferFrom(in.getChannel(), 0, in.getChannel().size());
		long end = System.nanoTime();
		ot.close();
		in.close();
		return end - begin;
	}
}