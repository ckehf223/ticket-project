package view;

import java.util.Scanner;

import controller.CustomerDAO;
import controller.CustomerRegisterManager;
import controller.PerformanceDAO;
import controller.PerformanceRegisterManager;
import model.CustomerVO;

public class PerformanceMarketMain {
	public static Scanner sc = new Scanner(System.in);
	public static CustomerVO cvo = new CustomerVO();
	public static void main(String[] args) {
		firstMenu();
	}
	
	//firstMenu
	public static void firstMenu() {
		int choiceNum = 0;
		boolean exitFlag = false;
		while(!exitFlag) {
			try {
				MenuViewer.firstMenuView();
				choiceNum = sc.nextInt();
				sc.nextLine();	//입력 버퍼클리어
				
				switch(choiceNum) {
				case FIRST_MENU_CHOICE.LOGIN:	//로그인
					MenuViewer.loginView();
					cvo = CustomerRegisterManager.customerLogin();
					if(cvo != null) {
						if(cvo.getCt_id().equals("admin")) {
							adminMenu();		//관리자메뉴
						}else {
							customerMenu();		//회원 메뉴
						}
					}else {
						System.out.println("로그인 실패");
						System.out.println("아이디/비밀번호 확인해주세요");
					}
					break;
				case FIRST_MENU_CHOICE.JOIN:		//회원 가입
					MenuViewer.joinMemberView();
					CustomerRegisterManager.customerRegister();
					break;
				case FIRST_MENU_CHOICE.SEARCH_USER:	//비밀번호 찾기
					MenuViewer.findMemberView();
					CustomerRegisterManager.customerSearch();
					break;
				case FIRST_MENU_CHOICE.EXIT:
					exitFlag = true;
					System.out.println("안녕히 가십시오.");
					break;
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			}catch(Exception e) {
				System.out.println("\n입력에 오류가 있습니다.\n프로그램을 다시 시작하세요.");
				e.printStackTrace();
				return;
			} 
		}//end of while
	}//end of firstMenu()
	
	//adminMenu
	public static void adminMenu() {
		int choiceNum = 0;
		boolean exitFlag = false;
		
		while(!exitFlag) {
			try {
				MenuViewer.adminMenuView();
				choiceNum = sc.nextInt();
				sc.nextLine();	//입력 버퍼 클리어
				
				switch(choiceNum) {
				case ADMIN_CHOICE.CT_LIST:		//회원 정보 보기
					CustomerDAO.getCustomerTotalList();
					break;
				case ADMIN_CHOICE.CT_DELETE:	//회원 삭제
					CustomerRegisterManager.deleteCustomer();
					break;
				case ADMIN_CHOICE.PF_INSERT:	//공연 추가		
					PerformanceRegisterManager.performanceRegister();
					break;
				case ADMIN_CHOICE.PF_LIST:		//공연 목록 보기
					PerformanceDAO.getPerformanceTotalList();
					break;
				case ADMIN_CHOICE.PF_DELETE:	//공연 삭제
					PerformanceRegisterManager.performanceDelete();
					break;
				case ADMIN_CHOICE.EXIT:			//로그아웃
					exitFlag = true;
					break;
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("\n입력에 오류가 있습니다.\n로그아웃 합니다.");
				return;
			}
		}//end of while
	}//end of adminMenu()
	
	//customerMenu
	public static void customerMenu() {
		int choiceNum = 0;
		boolean exitFlag = false;
		
		while(!exitFlag) {
			try {
				MenuViewer.mainMenuView();
				choiceNum = sc.nextInt();
				sc.nextLine();	//입력 버퍼 클리어
				
				switch(choiceNum) {
				case CUSTOMER_CHOICE.CT_SHOW_INFO:		//내 정보 보기
					cvo.printCustomer();
					break;
				case CUSTOMER_CHOICE.CT_UPDATE:			//내 정보 수정
					CustomerRegisterManager.customerUpdate(cvo);
					break;
				case CUSTOMER_CHOICE.CT_SHOW_PF:		//공연 정보 보기
					PerformanceDAO.getPerformanceTotalList();
					break;
				case CUSTOMER_CHOICE.CT_RESERVATION_PF:	//공연 예매 하기		
					break;
				case CUSTOMER_CHOICE.CT_SHOW_CART:		//예매 내역 보기	
					break;
				case CUSTOMER_CHOICE.CT_DELETE_PF:		//예매 공연 삭제
					break;
				case CUSTOMER_CHOICE.CT_DELETE_CART:	//예매 공연 모두 삭제
					break;
				case CUSTOMER_CHOICE.CT_PAYMENT:		//예매 공연 결제
					break;
				case CUSTOMER_CHOICE.CT_SHOW_PAYMENT_LIST:	//결제 내역 보기
					break;
				case CUSTOMER_CHOICE.SERVICE:			//고객센터 
					break;
				case CUSTOMER_CHOICE.EXIT:				//로그아웃
					exitFlag = true;
					break;                            
				default:
					System.out.println("해당 메뉴 번호만 입력하세요.");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("\n입력에 오류가 있습니다.\n로그아웃 합니다.");
				return;
			}
		}//end of while
	}//end of customerMenu()
}//end of performanceMarketMain class
