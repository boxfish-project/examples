package com.lenicliu.tutorials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.DecimalFormat;

/**
 * io
 * 
 * @author lenicliu
 *
 */
public class IO {

	public static final Charset			UTF_8	= Charset.forName("UTF-8");
	public static final int				K		= 1024;
	public static final int				M		= 1024 * K;
	public static final int				G		= 1024 * M;

	public static final String			HOST	= "127.0.0.1";
	public static final int				PORT	= 9999;

	public static final DecimalFormat	format	= new DecimalFormat("0.00");

	public static void write(String content, String file) throws IOException {
		FileOutputStream ot = new FileOutputStream(file);
		ot.write(content.getBytes(UTF_8));
		ot.close();
	}

	public static void writer(String content, String file) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), UTF_8);
		writer.write(content);
		writer.close();
	}

	public static String reader(String file) throws IOException {
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file), UTF_8);
		char[] buffer = new char[K];
		int read = reader.read(buffer);
		reader.close();
		return new String(buffer, 0, read);
	}

	public static String read(String file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		byte[] content = new byte[in.available()];
		in.read(content);
		in.close();
		return new String(content, UTF_8);
	}

	public static void copy(InputStream in, OutputStream ot) throws IOException {
		byte[] buffer = new byte[IO.M];
		int read = 0;
		while (true) {
			read = in.read(buffer);
			if (read == -1) {
				break;
			}
			ot.write(buffer, 0, read);
		}
	}

	public static void copy(FileChannel ic, FileChannel oc) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(IO.M);
		int read = 0;
		while (true) {
			buffer.clear();
			read = ic.read(buffer);
			if (read == -1) {
				break;
			}
			buffer.flip();
			oc.write(buffer);
		}
	}

	/**
	 * nio copy file
	 * 
	 * @param source
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static void nio(File source, File target) throws Exception {
		FileInputStream in = new FileInputStream(source);
		FileOutputStream ot = new FileOutputStream(target);

		FileChannel ic = in.getChannel();
		FileChannel oc = ot.getChannel();

		copy(ic, oc);
		// ic.transferTo(0, ic.size(), oc);

		ic.close();
		oc.close();

		in.close();
		ot.flush();
		ot.close();

	}

	/**
	 * io copy file
	 * 
	 * @param source
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static void io(File source, File target) throws Exception {

		FileInputStream in = new FileInputStream(source);
		FileOutputStream ot = new FileOutputStream(target);

		copy(in, ot);

		in.close();
		ot.flush();
		ot.close();
	}

	public static void net(File source, File target, double size) throws UnknownHostException, IOException {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ServerSocket server = new ServerSocket(PORT);

					Socket socket = server.accept();
					InputStream in = socket.getInputStream();
					OutputStream ot = new FileOutputStream(target);

					long begin = System.currentTimeMillis();
					copy(in, ot);
					long end = System.currentTimeMillis();
					double time = (end - begin) / 1000.0;
					System.out.println("server(net):\t" + format.format(size) + "m, " + format.format(time) + " s, " + format.format(size / time) + " mb/s");

					in.close();
					ot.flush();
					ot.close();
					socket.close();
					server.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				try {
					Socket socket = new Socket(HOST, PORT);

					OutputStream ot = socket.getOutputStream();
					InputStream in = new FileInputStream(source);

					long begin = System.currentTimeMillis();
					copy(in, ot);
					long end = System.currentTimeMillis();
					double time = (end - begin) / 1000.0;
					System.out.println("client(net):\t" + format.format(size) + "m, " + format.format(time) + " s, " + format.format(size / time) + " mb/s");

					in.close();
					ot.flush();
					ot.close();
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void main(String[] args) throws Exception {

		System.out.println(reader("in.txt"));
		System.out.println(read("in.txt"));

		write("Hi, Lenic Liu, 我来自古城西安!", "write.txt");
		writer("Hi, Lenic Liu, 我来自古城西安!", "writer.txt");

		int n = 5;

		// preparation
		File source = new File("source.iso");
		File target = new File("target.iso");

		double file_size = 1.0 * source.length() * n / IO.M;
		double nio_time = 0.0;
		double nio_speed = 0.0;
		double io_time = 0.0;
		double io_speed = 0.0;

		// loop for copying by nio
		long begin = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			nio(source, target);
		}
		long end = System.currentTimeMillis();
		nio_time = (end - begin) / 1000.0;
		nio_speed = file_size / nio_time;// mb/s

		// loop for copying by io
		begin = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			io(source, target);
		}
		end = System.currentTimeMillis();
		io_time = (end - begin) / 1000.0;
		io_speed = file_size / io_time;// mb/s

		// console
		System.out.println("size:\t" + format.format(file_size) + "m");
		System.out.println("nio:\t" + format.format(nio_time) + "s " + format.format(nio_speed) + "mb/s");
		System.out.println("io:\t" + format.format(io_time) + "s " + format.format(io_speed) + "mb/s");
		System.out.println("nio/io:\t" + format.format(nio_speed / io_speed));

		// transfer by network
		net(source, target, file_size / n);
	}
}