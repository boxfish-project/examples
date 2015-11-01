package com.lenicliu.example.io.showcase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import com.lenicliu.example.io.IO;

/**
 * 服务端演示字节流和字符转化
 * 
 * @author lenic
 *
 */
public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9999);
		Socket sockect = server.accept();
		
		write(read(sockect), "B.txt");

		sockect.close();
		server.close();
	}

	/**
	 * 从网络字节流中读取字符
	 * 
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	private static char[] read(Socket socket) throws IOException {
		char[] content = new char[IO.K];

		InputStream input = socket.getInputStream();
		Reader reader = new InputStreamReader(input);

		int count = reader.read(content);
		IO.console("read " + count + " chars from socket");

		reader.close();
		input.close();

		return content;
	}

	/**
	 * 把字符通过文件字节流写入磁盘
	 * 
	 * @param content
	 * @param filename
	 * @throws IOException
	 */
	private static void write(char[] content, String filename) throws IOException {
		OutputStream output = new FileOutputStream(filename);
		Writer writer = new OutputStreamWriter(output);

		writer.write(content);

		writer.close();
		output.close();
	}
}