package com.lenicliu.example.io;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class IO {

	public static final String	HOST	= "127.0.0.1";
	public static final int		PORT	= 9999;
	public static final int		K		= 1024;
	public static final int		M		= 1024 * K;
	public static final int		G		= 1024 * M;

	public static void console(char[] content) {
		if (content == null) {
			console((String) null);
		} else {
			console(new String(content));
		}
	}

	public static void console(byte[] content) {
		console(Arrays.toString(content));
	}

	public static void consoleToString(byte[] content) {
		consoleToString(content, null);
	}

	public static void consoleToString(byte[] content, Charset charset) {
		if (content == null) {
			console((String) null);
		} else {
			if (charset == null) {
				console(new String(content));
			} else {
				console(new String(content, charset));
			}
		}
	}

	public static void console(String content) {
		System.out.println(content);
	}
}