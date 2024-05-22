package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class ServerRegisterManager {

	public static void connectServer() throws IOException, InterruptedException {
		Scanner input = new Scanner(System.in);
		StringBuilder answer = new StringBuilder();
		boolean flag = false;
		
		String filePath = "D:/git/ticket-repository/BigBigProject/src/controller/connect.properties";
		Properties properties = new Properties();
		properties.load(new FileReader(filePath));
		String port = properties.getProperty("port");
		
		ServerSocket ss = new ServerSocket(Integer.parseInt(port));
		Socket cs = ss.accept();
		DataOutputStream dos = new DataOutputStream(cs.getOutputStream());

		Server server = new Server();
		server.setCs(cs);
		server.start();

		dos.writeUTF("────────────────1대1 문의 사항 고객센터───────────────");
		dos.writeUTF("*무엇을 도와드릴까요? 자유롭게 질문해 주세요 최선을 다하겠습니다.");
	
		while (!flag) {
			String message = input.nextLine();
			if (message.equals("exit")) {
				flag = true;
				continue;
			}
			answer.append(message);
			dos.writeUTF("상담사: " + message);
		}
		ServerDAO.setServiceContents(server.getCt_id(), server.getQuestion(), answer.toString());
		System.out.println("고객센터 서버 종료");
	}

	//서버 내부클래스
	public static class Server extends Thread {
		private Socket cs;
		private String ct_id;
		private String question;
		
		@Override
		public void run() {
			boolean flag = false;
			DataInputStream dis = null;
			String message = null;
			try {
				dis = new DataInputStream(cs.getInputStream());
				while (!flag) {
					message = dis.readUTF();
					System.out.println(message);
					if (message.contains("exit")) {
						flag = true;
						continue;
					}
					if (message != null) {
						System.out.print("**답변하기: ");
						String[] umg = message.split(":");
						ct_id = umg[0];
						question = umg[1];
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
		public Socket getCs() {
			return cs;
		}
		public void setCs(Socket cs) {
			this.cs = cs;
		}
		public String getCt_id() {
			return ct_id;
		}
		public void setCt_id(String ct_id) {
			this.ct_id = ct_id;
		}
		public String getQuestion() {
			return question;
		}
		public void setQuestion(String question) {
			this.question = question;
		}
		
		
	}

}
