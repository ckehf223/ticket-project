package view;

import java.util.Scanner;

import controller.CartDAO;
import controller.CartPaymentRegisterManager;
import controller.ClientDAO;
import controller.ClientRegisterManager;
import controller.CustomerDAO;
import controller.CustomerRegisterManager;
import controller.DeletePerformanceReigersterManager;
import controller.PerformanceDAO;
import controller.PerformanceRegisterManager;
import controller.ReservationRegisterManager;
import model.CustomerVO;

public class PerformanceMarketMain {
	public static Scanner input = new Scanner(System.in);
	public static CustomerVO cvo = new CustomerVO();
	public static void main(String[] args) {
		startMenu();
	}
	
	//startMenu
	public static void startMenu() {
		String regExp ="^[0-9]+$";
		String checkNum = null;
		int choiceNum = 0;
		boolean exitFlag = false;
		while(!exitFlag) {
			try {	
				MenuViewer.startMenuView();						//시작화면 view
				checkNum = input.nextLine();
				//패턴검색
				if(checkNum.matches(regExp)) {
					choiceNum = Integer.parseInt(checkNum);
					if(choiceNum > 0 && choiceNum <=4) {
						switch(choiceNum) {
						case FIRST_MENU_CHOICE.LOGIN:			//로그인
							MenuViewer.loginView();
							cvo = CustomerRegisterManager.customerLogin();
							if(cvo != null) {
								if(cvo.getCt_id().equals("admin")) {
									adminMenu();				//관리자메뉴
								}else {
									customerMenu();				//회원 메뉴
								}
							}else {
								System.out.println("로그인 실패");
								System.out.println("아이디/비밀번호 확인해주세요");
							}
							break;
						case FIRST_MENU_CHOICE.JOIN:			//회원 가입
							MenuViewer.joinMemberView();
							CustomerRegisterManager.customerRegister();
							break;
						case FIRST_MENU_CHOICE.SEARCH_USER:		//비밀번호 찾기
							MenuViewer.findMemberView();
							CustomerRegisterManager.customerSearch();
							break;
						case FIRST_MENU_CHOICE.EXIT:
							exitFlag = true;
							System.out.println("안녕히 가십시오.");
							break;
						}
					}else {
						System.out.println("0-4까지 입력가능합니다. 다시입력해주세요!");
					}
				}else {
					System.out.println("잘못입력하셨습니다.");
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
		String regExp ="^[0-9]+$";
		String checkNum = null;
		int choiceNum = 0;
		boolean exitFlag = false;
		
		while(!exitFlag) {
			try {
				MenuViewer.adminMenuView();					//관리자 메인메뉴 view
				checkNum = input.nextLine();
				//패턴검색
				if(checkNum.matches(regExp)) {
					choiceNum = Integer.parseInt(checkNum);
					if(choiceNum > 0 && choiceNum <= 6) {
						switch(choiceNum) {
						case ADMIN_CHOICE.CT_LIST:			//회원 정보 보기
							CustomerDAO.getCustomerTotalList();
							break;
						case ADMIN_CHOICE.CT_DELETE:		//회원 삭제
							CustomerRegisterManager.deleteCustomer();
							break;
						case ADMIN_CHOICE.PF_INSERT:		//공연 추가		
							PerformanceRegisterManager.performanceRegister();
							break;
						case ADMIN_CHOICE.PF_LIST:			//공연 목록 보기
							PerformanceDAO.getPerformanceTotalList();
							break;
						case ADMIN_CHOICE.PF_DELETE:		//공연 삭제
							PerformanceRegisterManager.performanceDelete();
							break;
						case ADMIN_CHOICE.EXIT:				//로그아웃
							exitFlag = true;
							break;
						}
					}else {
						System.out.println("0-6까지 입력가능합니다 다시 입력해주세요!");
					}
				}else {
					System.out.println("잘못입력하셨습니다.");
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
		String regExp ="^[0-9]+$";
		String checkNum = null;
		int choiceNum = 0;
		boolean exitFlag = false;
		
		while(!exitFlag) {
			try {
				MenuViewer.mainMenuView();							//고객 메인메뉴 view
				checkNum = input.nextLine();
				//패턴검색
				if(checkNum.matches(regExp)) {
					choiceNum = Integer.parseInt(checkNum);
					if(choiceNum > 0 && choiceNum <=10) {
						switch(choiceNum) {
						case CUSTOMER_CHOICE.CT_SHOW_INFO:			//내 정보 보기
							cvo.printCustomer();
							break;
						case CUSTOMER_CHOICE.CT_UPDATE:				//내 정보 수정
							CustomerRegisterManager.customerUpdate(cvo);
							break;
						case CUSTOMER_CHOICE.CT_SHOW_PF:			//공연 정보 보기
							PerformanceDAO.getPerformanceTotalList();
							break;
						case CUSTOMER_CHOICE.CT_RESERVATION_PF:		//공연 예매 하기
							ReservationRegisterManager.reservationPerformance(cvo.getCt_id(), cvo.getCt_pw());
							break;
						case CUSTOMER_CHOICE.CT_SHOW_CART:			//예매 내역 보기	
							MenuViewer.printCart();
							CartDAO.getCartList(cvo.getCt_id());
							break;
						case CUSTOMER_CHOICE.CT_DELETE_CART:		//예매 공연 모두 삭제
							DeletePerformanceReigersterManager.cartDeletePerformance(cvo.getCt_id());
							break;
						case CUSTOMER_CHOICE.CT_PAYMENT:			//예매 공연 결제
							CartPaymentRegisterManager.cartPayment(cvo);
							break;
						case CUSTOMER_CHOICE.CT_SHOW_PAYMENT_LIST:	//결제 내역 보기
							MenuViewer.printPaymentCart();
							CartDAO.getPaymentCartList(cvo.getCt_id());
							break;
						case CUSTOMER_CHOICE.SERVICE:				//고객센터 
							serviceManagement(cvo);
							break;
						case CUSTOMER_CHOICE.EXIT:					//로그아웃
							exitFlag = true;
							break;                            
						}
						
					}else {
						System.out.println("0-10 까지 입력가능합니다. 다시 입력해주세요!");
					}
				}else {
					System.out.println("잘못입력하셨습니다.");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("\n입력에 오류가 있습니다.\n로그아웃 합니다.");
				return;
			}
		}//end of while
	}//end of customerMenu()
	
	//고객센터 메뉴
	public static void serviceManagement(CustomerVO cvo) {
		String regExp ="^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		try {
			MenuViewer.serviceMenuView();
			checkNum = input.nextLine();
			if(checkNum.matches(regExp)) {
				selectNum = Integer.parseInt(checkNum);
				if(selectNum > 0 && selectNum <=2) {
					switch(selectNum) {
					case SERVICE_CHOICE.INQUIRY: 
						ClientRegisterManager.inquiryService(cvo);
						break;
					case SERVICE_CHOICE.INQUIRY_LIST:
						MenuViewer.printInquiry();
						ClientDAO.inquiryServiceList(cvo.getCt_id());
						break;
					}
				}else {
					System.out.println("1-2까지 입력 가능합니다.");
				}
			}else {
				System.out.println("잘못입력하셨습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//end of serviceManagement
}//end of performanceMarketMain class
