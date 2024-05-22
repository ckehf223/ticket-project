package project.service;
//5. 정지시키는 방법은 클라이언트가 종료 명령을 보내면 그 해당된 소켓만 종료시킨다.

//4. Thread 실행 run 만든다 (보내온 글을 읽고 -> 모든 클라이언트 소켓이 보낸다 ) 무한루프에 빠진다.

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	public static ArrayList<ClientSocketThread> list = new ArrayList<Server.ClientSocketThread>();
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket ss = new ServerSocket(9000);
		boolean flag = false;
			Socket cs = ss.accept();
			ClientSocketThread cst = new ClientSocketThread(cs);
			cst.start();
			System.out.println("********************************");
			System.out.println("\t\t상담사 답변 ");
			while(!flag) {
				Thread.sleep(200);
				String message = sc.nextLine();
				if(message.equals("exit")) {
					flag = true;
					continue;
				}
				cst.dos.writeUTF("상담사 : "+message);
			}

	}// end of main

	// 내부클래스를 만든다.
	public static class ClientSocketThread extends Thread {
		// 멤버변수
		private Socket cs;
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isActive;

		// 생성자
		public ClientSocketThread(Socket cs) throws IOException {
			super();
			this.cs = cs;
			this.dis = new DataInputStream(cs.getInputStream());
			this.dos = new DataOutputStream(cs.getOutputStream());
			this.isActive = true;
		}

		@Override
		public void run() {
			String message = null;
			try {
				dos.writeUTF("1대1 문의 사항 고객센터입니다");
				dos.writeUTF("최선을 다하겠습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}

			while (isActive) {
				try {
					message = this.dis.readUTF();
					System.out.println(message);
					if (message.contains("exit") == true) {
						this.dos.writeUTF("안녕히 가십시오.");
						this.isActive = false;
					}
					for (ClientSocketThread data : list) {
						data.dos.writeUTF(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // end of while
			try {
				this.dis.close();
				this.dos.close();
				this.cs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// end of run

		public DataInputStream getDis() {
			return dis;
		}

		public void setDis(DataInputStream dis) {
			this.dis = dis;
		}

		public DataOutputStream getDos() {
			return dos;
		}

		public void setDos(DataOutputStream dos) {
			this.dos = dos;
		}

	}// end of ClientSocketThread
}// end of Server
