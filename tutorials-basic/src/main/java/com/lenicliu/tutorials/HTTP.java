package com.lenicliu.tutorials;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HTTP {

	private static final int PORT = 8080;

	public static void once() throws Exception {
		ServerSocket server = new ServerSocket(PORT);
		Socket socket = server.accept();
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		StringBuffer req = new StringBuffer();
		while (true) {
			String input = reader.readLine();
			if (input == null || input.length() == 0) {
				break;
			}
			req.append(input).append("\n");
		}
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		StringBuffer res = new StringBuffer();
		res.append("HTTP/1.1 200 OK").append("\n");
		res.append("Date: " + new Date()).append("\n");
		res.append("Server: Simple(Java)").append("\n");
		res.append("Last-Modified: " + new Date()).append("\n");
		res.append("Content-Type: text/html; charset=UTF-8").append("\n");
		res.append("").append("\n");
		res.append("<html>").append("\n");
		res.append("<head>").append("\n");
		res.append("<title>An Example Page</title>").append("\n");
		res.append("</head>").append("\n");
		res.append("<body>").append("\n");
		res.append("Hello World, this is a very simple HTML document.").append("\n");
		res.append("</body>").append("\n");
		res.append("</html>").append("\n");
		writer.write(res.toString());
		writer.flush();
		server.close();

		System.out.println(req.toString());
		System.out.println("============");
		System.out.println(res.toString());
	}

	public static void main(String[] args) throws Exception {
		String host = "www.oschina.net";
		Socket request = new Socket(host, 80);
		request.getOutputStream().write("GET / HTTP/1.1".getBytes());
		request.getOutputStream().write("\n".getBytes());
		request.getOutputStream().write(("Host: " + host).getBytes());
		request.getOutputStream().write("\n".getBytes());
		request.getOutputStream().write("\n".getBytes());
		request.getOutputStream().flush();
		List<byte[]> buffered = new ArrayList<byte[]>();
		byte[] buffer = new byte[1024];
		while (true) {
			int read = request.getInputStream().read(buffer);
			if (read < 0) {
				break;
			}
			buffered.add(Arrays.copyOf(buffer, read));
		}
		for (byte[] bytes : buffered) {
			System.out.println(new String(bytes));
		}
		request.close();
	}
}