package project.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceContend {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Service> list = new ArrayList<Service>();

	public static void main(String[] args) {

		boolean flag = false;
		try {
			while (!flag) {
				System.out.println("1.입력 2. 출력 3.삭제 4.저장 5.로딩 6.종료");
				System.out.print("번호입력: ");
				int num = sc.nextInt();
				sc.nextLine();
				switch (num) {
				case 1:
					inputContend();
					break;
				case 2:
					printContend();
					break;
				case 3:
					deleteContend();
					break;
				case 4:
					saveContend();
					break;
				case 5:
					list = serviceRoading();
					break;
				case 6:
					flag = true;
					break;
				}// end of switch

			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("The end");

	}// end of main

	public static void inputContend() {
		
		System.out.print("카테고리입력: ");
		String category = sc.nextLine();
		
		System.out.print("제목입력: ");
		String head = sc.nextLine();

		System.out.print("내용입력: ");
		String contend = sc.nextLine();
		Service service = new Service(category,head, contend);
		list.add(service);
	}

	public static void printContend() {
		for (Service data : list) {
			System.out.println("카테고리: ["+data.getCategory()+"]");
			System.out.println("제목: " + data.getServiceHead());
			System.out.println("내용: " + data.getContend());
		}
	}

	public static void deleteContend() {
		boolean flag = false;
		System.out.print("제거할 제목을 입력해주세요: ");
		String head = sc.nextLine();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getServiceHead().equals(head)) {
				list.get(i).getServiceHead().toString();
				list.remove(i);
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("찾으시는 제목이 없습니다.");
		}
	}

	public static void saveContend() {
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("contend.db"))));
			oos.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("저장 오류");
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<Service> serviceRoading() {
		ArrayList<Service> sList = new ArrayList<>();
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("contend.db"))));
			sList = (ArrayList<Service>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로딩 오류");
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sList;
	}

}
