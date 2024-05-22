package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

import model.CustomerVO;

public class ClientRegisterManager {
	
	//소켓통신을 통해 서버 연결후 1대1문의 하기
	public static void inquiryService(CustomerVO cvo) throws FileNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		String filePath ="D:/git/ticket-repository/BigBigProject/src/controller/connect.properties";
		Properties properties = new Properties();
		properties.load(new FileReader(filePath));
		String ip = properties.getProperty("ip");
		String port = properties.getProperty("port");
		boolean flag = false;
		
		try {
			Socket cs = new Socket(ip, Integer.parseInt(port));
			DataOutputStream dos = new DataOutputStream(cs.getOutputStream());
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					boolean flag = false;
					DataInputStream dis = null;
					try {
						dis = new DataInputStream(cs.getInputStream());
						while (!flag) {
							String data = dis.readUTF();
							System.out.println(data);
							if (data.contains("exit")) {
								flag = true;
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
			Thread.sleep(1000);
			System.out.print("질문을 입력하세요: ");
			while (!flag) {
				String message = input.nextLine();
				if (message.equals("exit")) {
					flag = true;
					continue;
				}
				dos.writeUTF(cvo.getCt_id()+":" + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end of inquiryService
	
}
