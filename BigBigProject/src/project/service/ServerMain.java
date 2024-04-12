package project.service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(14276);
			Socket cs = ss.accept();
			ClientSocketThread cst = new ClientSocketThread(cs);
			cst.start();
	}//end of main

	
	//로딩 
	public static ArrayList<Service> serviceLoading(){
		ArrayList<Service> sList = null;
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("contend.db"))));
			sList = (ArrayList<Service>)ois.readObject();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("로딩 오류");
		}finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sList;
	}
	
	//내부클래스
	public static class ClientSocketThread extends Thread{
		public ArrayList<Service> list;
		private Socket cs;
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isActive;
		
		public ClientSocketThread(Socket cs) throws IOException {
			super();
			this.cs = cs;
			this.dis = new DataInputStream(cs.getInputStream());
			this.dos = new DataOutputStream(cs.getOutputStream());
			this.isActive = false;
			this.list = serviceLoading();
		}

		@Override
		public void run() {
			String message =null;
			
			while(!isActive) {
				this.list = serviceLoading();
				boolean flag1 = false;
				try {
					this.dos.writeUTF("키워드를 입력하세요!: ");
					message = this.dis.readUTF();
					if(message.equals("exit")) {
						this.isActive = true;
						continue;
					}else {
						int count =1;
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getCategory().contains(message)) {
								list.get(i).setServiceHead(count+"."+list.get(i).getServiceHead());
									this.dos.writeUTF(list.get(i).getServiceHead());
									count++;
									flag1 = true;
							}
						}
						if(!flag1) {
							this.dos.writeUTF("찾으시는 키워드가 없습니다.");
							this.dos.writeUTF("..");
							continue;
						}else {
							this.dos.writeUTF("..");
							message = this.dis.readUTF();
							for(int i=0;i<list.size();i++) {
								if(list.get(i).getServiceHead().contains(message)) {
									this.dos.writeUTF("질문: "+list.get(i).getServiceHead().substring(2));
									this.dos.writeUTF("답변: "+list.get(i).getContend());
									break;
								}
							}
							message = this.dis.readUTF();
							if(message.equals("N")) {
								isActive = true;
							}
						}
						
						if(!flag1) {
							this.dos.writeUTF("찾으시는 정보가 없습니다.");
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}//end of while
			try {
				this.dis.close();
				this.dos.close();
				this.cs.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("자원 닫기 오류");
			}
		}//end of run
	}//end of clientSocketThread
}
