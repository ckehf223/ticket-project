package controller;

import java.util.Scanner;

import model.CustomerVO;

public class CustomerRegisterManager {
	public static Scanner sc = new Scanner(System.in);
	//회원 가입
	public static void customerRegister() throws Exception{
		String ct_id = null; 			// 아이디
		String ct_pw = null; 			// 비밀번호
		String ct_name = null; 			// 이름
		int ct_age = 0;					// 나이
		String ct_phone = null; 		// 전화번호
		String ct_address = null; 		// 주소
		boolean idCheck = true;			//중복체크
		
		System.out.println("회원 정보 입력");
		while(idCheck) {
			System.out.print("아이디를 입력하세요(15자 이내) : ");
			ct_id = sc.nextLine();
			idCheck = CustomerDAO.getCustomerIdOverlap(ct_id);
			if(idCheck) {
				System.out.println("중복된 아이디입니다. 다시 입력하세요");
			}
		}
		
		System.out.print("비밀번호(20자 이내) :");
		ct_pw = sc.nextLine();
		System.out.print("이름 :");
		ct_name = sc.nextLine();
		System.out.print("나이 :");
		ct_age = sc.nextInt();
		sc.nextLine();	//입력 버퍼클리어
		System.out.print("전화번호(숫자만) :");
		ct_phone = sc.nextLine();
		System.out.print("주소 :");
		ct_address = sc.nextLine();
		
		CustomerVO cvo = new CustomerVO(ct_id, ct_pw, ct_name, ct_age, ct_phone, ct_address);
		CustomerDAO.setCustomerRegister(cvo);
	}
	
	//회원 정보 수정
	public static void customerUpdate(CustomerVO cvo) throws Exception {
		String ct_pw = null; 			// 수정할 비밀번호
		String ct_phone = null; 		// 전화번호
		String ct_address = null; 		// 주소

		System.out.println(cvo.getCt_id()+"회원 정보 수정");
		System.out.print("새로운 비밀번호 (20자 이내) :");
		ct_pw = sc.nextLine();
		System.out.print("새로운 전화번호 :");
		ct_phone = sc.nextLine();
		System.out.print("새로운 주소 :");
		ct_address = sc.nextLine();
		
		cvo.setCt_pw(ct_pw);
		cvo.setCt_phone(ct_phone);
		cvo.setCt_address(ct_address);
		CustomerDAO.setCustomerUpdate(cvo);
		cvo.printCustomer();
	}
	
	//비밀번호 찾기
	public static void customerSearch() throws Exception {
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = sc.nextLine();
		
		CustomerDAO.getCustomerSearch(id, phone);
	}
	
	//로그인
	public static CustomerVO customerLogin() throws Exception{
		CustomerVO cvo = null;
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
		cvo = CustomerDAO.getCustomerLogin(id, pw);
		return cvo;
	}
	
	//회원 삭제
	public static void deleteCustomer() throws Exception{
		CustomerDAO.getCustomerTotalList();
		
		System.out.print("삭제할 회원 ID : ");
		String id = sc.nextLine();
		CustomerDAO.setCustomerDelete(id);
	}
}
