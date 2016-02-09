package com.lenicliu.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UsageNIO {

	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream("nio0.txt");
		FileOutputStream ot = new FileOutputStream("nio1.txt");

		ot.getChannel().transferFrom(in.getChannel(), 0, in.getChannel().size());

		in.close();
		ot.close();
	}
}