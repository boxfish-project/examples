package com.lenicliu.example.io.showcase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.lenicliu.example.io.IO;

/**
 * 客户端演示字节流读写操作
 * 
 * @author lenic
 *
 */
public class Client {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(IO.HOST, IO.PORT);

		write(read("A.txt"), socket);

		socket.close();
	}

	/**
	 * 把字节数组写入网络字节流
	 * 
	 * @param content
	 * @param socket
	 * @throws IOException
	 */
	private static void write(byte[] content, Socket socket) throws IOException {
		OutputStream output = socket.getOutputStream();

		output.write(content);

		output.close();
	}

	/**
	 * 从文件字节流读取字节数组
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static byte[] read(String filename) throws IOException {
		InputStream input = new FileInputStream(filename);
		
		byte[] content = new byte[input.available()];
		
		int count = input.read(content);
		IO.console("read " + count + " bytes from A.txt");
		
		input.close();
		
		return content;
	}
}