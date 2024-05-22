package project.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Server {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {
		connectServer();
	}// end of main

	public static void connectServer() throws IOException, InterruptedException {
		String filePath ="D:/git/ticket-repository/BigBigProject/src/controller/connect.properties";
		Properties properties = new Properties();
		properties.load(new FileReader(filePath));
		String port = properties.getProperty("port");
		ServerSocket ss = new ServerSocket(Integer.parseInt(port));
		boolean flag = false;
		Socket cs = ss.accept();
		DataOutputStream dos = new DataOutputStream(cs.getOutputStream());
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				String message = null;
				boolean flag = false;
				DataInputStream dis = null;
				try {
					dis = new DataInputStream(cs.getInputStream());
					while (!flag) {
						message = dis.readUTF();
						System.out.println(message);
						if (message.contains("exit")) {
							flag = true;
							continue;
						}
					} // end of while
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					dis.close();
					cs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		thread.start();
		dos.writeUTF("1대1 문의 사항 고객센터입니다");
		dos.writeUTF("최선을 다하겠습니다.");
		System.out.println("********************************");
		System.out.println("\t\t상담사 답변 ");
		while (!flag) {
			String message = sc.nextLine();
			if (message.equals("exit")) {
				flag = true;
				continue;
			}
			dos.writeUTF(message);
		}
		System.out.println("고객센터 서버 종료");
	}

}// end of Server
