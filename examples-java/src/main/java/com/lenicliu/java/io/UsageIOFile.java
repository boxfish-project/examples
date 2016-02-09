package com.lenicliu.java.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UsageIOFile {

	static final int	K	= 1024;
	static final int	M	= 1024 * K;
	static final int	G	= 1024 * M;
	static final int	T	= 1024 * G;

	public static void main(String[] args) throws IOException {
		File dir = new File("file");

		deleteFile(dir);

		dir.mkdirs();

		String content = "Hello World.";
		File f0 = new File(dir.getPath() + File.separator + "f0.txt");
		writeFile(f0, content);

		content = readFile(f0);
		System.out.println(content);

		File f1 = new File(dir.getPath() + File.separator + "f1.txt");

		copyFile(f0, f1);

		content = readFile(f1);
		System.out.println(content);

		File[] files = dir.listFiles();
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}

		System.out.println("disk\ttotal\tusable\tfree");
		File[] roots = File.listRoots();
		for (File root : roots) {
			System.out.println(root.getAbsolutePath() + "\t" + root.getTotalSpace() / G + "\t"
					+ root.getUsableSpace() / G + "\t" + root.getFreeSpace() / G);
		}
	}

	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					deleteFile(f);
				}
			} else if (file.isFile()) {
				file.delete();
			}
		}
	}

	public static void writeFile(File file, String content) throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		writer.close();
	}

	public static String readFile(File file) throws IOException {
		FileReader reader0 = new FileReader(file);
		char[] buf = new char[K];
		StringBuffer buffer = new StringBuffer();
		int read = -1;
		while ((read = reader0.read(buf)) != -1) {
			buffer.append(buf, 0, read);
		}
		reader0.close();
		return buffer.toString();
	}

	public static void copyFile(File src, File dest) throws IOException {
		FileReader reader = new FileReader(src);
		FileWriter writer = new FileWriter(dest);
		char[] buf = new char[K];
		int read = -1;
		while ((read = reader.read(buf)) != -1) {
			writer.write(buf, 0, read);
		}
		writer.close();
		reader.close();
	}
}