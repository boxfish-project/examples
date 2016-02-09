package com.lenicliu.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UsageIOStream {
	static final int	K	= 1024;
	static final int	M	= 1024 * K;
	static final int	G	= 1024 * M;
	static final int	T	= 1024 * G;

	public static void main(String[] args) throws IOException {
		String content = "Hello Stream.";
		write("f0.txt", content);

		content = read("f0.txt");
		System.out.println(content);

		copy("f0.txt", "f1.txt");

		content = read("f0.txt");
		System.out.println(content);

	}

	public static String read(String file) throws IOException {
		InputStream in = new FileInputStream(file);
		byte[] content = new byte[in.available()];
		in.read(content);
		in.close();
		return new String(content);
	}

	public static void write(String file, String content) throws IOException {
		OutputStream ot = new FileOutputStream(file);
		ot.write(content.getBytes());
		ot.close();
	}

	public static int copy(String src, String dest) throws IOException {
		FileInputStream in = new FileInputStream(src);
		FileOutputStream ot = new FileOutputStream(dest);
		byte[] buffer = new byte[M];
		int read = -1, len = 0;
		while ((read = in.read(buffer)) != -1) {
			ot.write(buffer, 0, read);
			len += read;
		}
		ot.close();
		in.close();
		return len;
	}
}