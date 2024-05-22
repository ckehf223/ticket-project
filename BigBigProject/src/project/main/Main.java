package project.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import project.cart.Cart;
import project.member.Admin;
import project.member.Customer;
import project.performance.Performance;
import project.service.Service;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static ArrayList<Performance> performanceList = new ArrayList<Performance>();
	public static ArrayList<Cart> cartList = new ArrayList<Cart>();
	public static Cart cart = new Cart();
	public static Customer customer = new Customer();
	public static Admin admin = new Admin();
	public static ArrayList<Service> serviceList = new ArrayList<>();

	public static void main(String[] args) {
		int numberSelection = 0;
		boolean exitFlag = false;
		while (!exitFlag) {
			try {
				adminLoading();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				customerList = customerLoading();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				performanceList = performanceLoading();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				cartList = cartLoading();
			} catch (Exception e) {
				e.printStackTrace();
			}
			numberSelection = firstScreen();
			if (numberSelection == 4) {
				exitFlag = true;
				continue;
			}
			try {
				
				switch (numberSelection) {
				case 1:
					customer = loginCheck(customerList);
					if (!(customer.getId() == null)) {
						if (customer.getId().equals(admin.getAdminID())
								&& customer.getPassword() == admin.getAdminPW()) {
							adminMenu(customerList);
							continue;
						} else {
							try {
								cart = cartCheck();
							} catch (Exception e) {
								System.out.println("카트 체크 오류");
							}
							boolean flag = false;
							int customerSelection = 0;
							while (!flag) {
								try {
									customerSelection = customerMenu();
									switch (customerSelection) {
									case 1:
										myInformation(); // 고객 정보 확인
										break;
									case 2:
										searchPerformance(); // 공연 정보 보기
										break;
									case 3:
										performanceReservation(); // 공연 예매
										break;
									case 4:
										printCartItem(); // 예매내역 보기
										break;
									case 5:
										cartRemoveItem(); // 예매내역 항목 삭제
										break;
									case 6:
										deleteAllCartItem(); // 예매내역 전체 삭제
										break;
									case 7:
										cartPaying(); // 결제하기
										break;
									case 8:
										printPaymentList(); // 결제내역 보기
										break;
									case 9:
										savePerformanceList(performanceList); // 공연저장
										cartLastSave(cart, cartList); // 카트 저장
										flag = true; // 종료
										break;
									case 10:
										serviceList = serviceLoading(); // 자주하는 질문 로딩
										connectService(serviceList); // 고객센터 연결

										break;
									}// end of switch

								} catch (Exception e) {
									System.out.println("잘못된 선택하셨습니다.");
									e.printStackTrace();
								} // end of try-catch
							} // end of while
						} // end of else

					}
					break;
				case 2:
					joinMemberShip(customerList); // 회원가입
					break;
				case 3:
					findCustomerInfo(customerList); // ID PW 찾기
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // end of while
		System.out.println("The end");
	}// end of main

	// 로그인 or 회원가입
	public static int firstScreen() {
		String regExp = "^[0-9]+$";
		String strNum = null;
		boolean flag = false;
		int num = 0;
		while (!flag) {
			System.out.println("┌─────────────────────────────────────────┐");
			System.out.println("│ 1.로그인   2.회원가입  3.ID/PW 찾기   4.종료   │");
			System.out.println("└─────────────────────────────────────────┘");
			System.out.print("번호를 입력하세요: ");
			strNum = sc.nextLine();
			if (strNum.matches(regExp)) {
				num = Integer.parseInt(strNum);
				if (num > 0 && num <= 4) {
					flag = true;
				} else {
					System.out.println("1~4까지 입력하세요!");
				}
			} else {
				System.out.println("잘못입력하셨습니다. ");
			}
		} // end of while
		return Integer.parseInt(strNum);
	}// end of firstScreen

	// 로그인
	public static Customer loginCheck(ArrayList<Customer> list) {
		boolean flag = false;
		Customer cus = new Customer();
		
		System.out.println("*********************");
		System.out.println("*    로   그   인     *");
		System.out.println("*********************");
		System.out.print("아이디를 입력하세요 : ");
		String ID = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String pin = sc.nextLine();

		if (admin.getAdminID().equals(ID) && admin.getAdminPW() == Integer.parseInt(pin)) {
			cus = new Customer(admin.getAdminID(), admin.getAdminPW());
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId().equals(ID) && list.get(i).getPassword() == Integer.parseInt(pin)) {
					System.out.println("로그인 성공");
					cus = new Customer(list.get(i).getName(), list.get(i).getPhone(), list.get(i).getAddress(),
							list.get(i).getId(), list.get(i).getPassword(), list.get(i).getAge(),
							list.get(i).getSaleRatio(), list.get(i).getGrade(), list.get(i).getAccumulatedPayment(),
							list.get(i).getMileage(), list.get(i).getMileageSale());
					flag = true;
					break;
				}
			} // end of for
			if (flag == false) {
				System.out.println("입력된 회원이 없습니다.");
			}
		}
		return cus;
	}// end of loginCheck

	// 회원가입
	public static void joinMemberShip(ArrayList<Customer> list) {
		boolean flag = false;
		System.out.println("****************************");
		System.out.println("*      회  원  가  입        *");
		System.out.println("****************************");
		System.out.print("아이디를 입력하세요: ");
		String ID = sc.nextLine();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(ID)) {
				System.out.println("중복된 아이디가 존재합니다 다시입력해주세요! ");
				flag = false;
				break;
			}
		}
		if (!flag) {
			System.out.print("비밀번호를 입력하세요(4자리 숫자만 입력) : ");
			String pin = sc.nextLine();
			System.out.print("이름을 입력하세요: ");
			String name = sc.nextLine();
			System.out.print("나이를 입력하세요: ");
			String age = sc.nextLine();
			System.out.print("전화번호를 입력하세요(010 숫자만 입력): ");
			String phone = "010" + sc.nextLine();
			System.out.print("주소를 입력하세요: ");
			String address = sc.nextLine();
			Customer user = new Customer(name, Integer.parseInt(age), phone, address, ID, Integer.parseInt(pin));
			list.add(user);
			cart.setId(ID);
			cart.setPw(Integer.parseInt(pin));
			cartList.add(cart);
			saveCartList(cartList);
			saveCustomerList(list);
		}
	}// end of joinMemberShip

	// 회원 정보 저장
	public static void saveCustomerList(ArrayList<Customer> list) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("customer.db"))));
			oos.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원정보 저장 오류");
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// end of saveCustomerList

	// customer 정보 로딩
	public static ArrayList<Customer> customerLoading() {
		ArrayList<Customer> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("customer.db"))));
			list = (ArrayList<Customer>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("회원 정보 로딩오류");
		} catch (Exception e) {
			System.out.println("회원 정보 로딩오류");
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}// end of customerRoading

	// 관리자메뉴
	public static void adminMenu(ArrayList<Customer> list) {
		String regExp = "^[0-9]+$";
		String strNum = null;
		boolean flag = false;
		int num = 0;
		while (!flag) {
			System.out.println("***************************************");
			System.out.println("* 1.회원 정보 출력    * 4.공연 정보 출력       ");
			System.out.println("* 2.회원 삭제        * 5.공연 삭제          ");
			System.out.println("* 3.공연 추가        * 6.로그아웃           ");
			System.out.println("***************************************");
			System.out.print("**번호를 입력하세요: ");
			strNum = sc.nextLine();
			if (strNum.matches(regExp)) {
				num = Integer.parseInt(strNum);
				if (num > 0 && num <= 6) {
					switch (num) {
					case 1:
						list.forEach(value -> System.out.println(value));
						break;
					case 2:
						deleteMember(list);
						break;
					case 3:
						insertPerformance(performanceList);
						break;
					case 4:
						if (performanceList.size() == 0) {
							System.out.println("등록된 공연 정보가 없습니다.");
						} else {
							cart.printPerformanceList(performanceList);
						}
						break;
					case 5:
						deletePerformance(performanceList);
						break;
					case 6:
						flag = true;
						break;
					}
				} else {
					System.out.println("1~6까지 입력하세요!");
				}
			} else {
				System.out.println("잘못입력하셨습니다. ");
			}
		} // end of while

	}// end of adminMenu

	// 관리자메뉴 회원탈퇴시키기
	public static void deleteMember(ArrayList<Customer> list) {
		boolean flag = false;
		System.out.print("탈퇴시킬 아이디를 입력하세요: ");
		String ID = sc.nextLine();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(ID)) {
				flag = true;
				System.out.println(list.get(i).toString());
				System.out.println("정말 삭제하시겠습니까? Y | N");
				String str = sc.nextLine();
				if (str.toUpperCase().equals("Y")) {
					list.remove(i);
					saveCustomerList(list);
				}
			}
		} // end of for
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getId().equals(ID)) {
				cartList.remove(i);
				saveCartList(cartList);
			}
		}
		if (flag == false) {
			System.out.println("일치하는 회원이 없습니다.");
			System.out.println("입력하신 아이디를 확인해주세요!");
		}
	}// end of deleteMember

	// 공연 정보 추가
	private static void insertPerformance(ArrayList<Performance> list) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
		String strDate = "P" + formatter.format(date);

		System.out.print("공연ID: " + strDate);
		System.out.println();
		System.out.print("공연명: ");
		String name = sc.nextLine();
		System.out.print("장르: ");
		String genre = sc.nextLine();
		System.out.print("공연일: ");
		String day = sc.nextLine();
		System.out.print("장소: ");
		String venue = sc.nextLine();
		System.out.print("관람연령(숫자): ");
		String limitAge = sc.nextLine();
		System.out.print("좌석수(숫자): ");
		String seats = sc.nextLine();
		System.out.print("가격(숫자): ");
		String price = sc.nextLine();

		list.add(new Performance(strDate, name, genre, day, venue, Integer.parseInt(limitAge), seats,
				Integer.parseInt(price)));
		savePerformanceList(list);

	}// end of insertPerformance

	// 공연 정보 삭제
	public static void deletePerformance(ArrayList<Performance> list) {
		boolean flag = false;
		System.out.print("삭제하실 공연ID를 입력하세요: ");
		String id = sc.nextLine();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPerformanceID().equals(id)) {
				cart.printPerformance(list.get(i));
				flag = true;
				System.out.println("정말 삭제하시겠습니까? Y | N");
				String str = sc.nextLine();
				if (str.toUpperCase().equals("Y")) {
					list.remove(i);
					savePerformanceList(list);
					break;
				}
			}
		}
		if (!flag) {
			System.out.println("입력하신 공연ID는 없습니다 다시 확인해주세요!");
		}
	}// end of deletePerformance

	// 공연 정보 저장
	private static void savePerformanceList(ArrayList<Performance> list) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("performance.db"))));
			oos.writeObject(list);
		} catch (IOException e) {
			System.out.println("공연 정보 저장오류" + e);
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				System.out.println("공연 정보 저장오류" + e);
			}
		}
	}// end of saveList

	// 공연 정보 로딩
	public static ArrayList<Performance> performanceLoading() {
		ArrayList<Performance> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("performance.db"))));
			list = (ArrayList<Performance>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("공연 정보 로딩 오류");
		} catch (Exception e) {
			System.out.println("공연 정보 로딩 오류");
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}// end of performanceRoading

	// 카트 정보 저장
	public static void saveCartList(ArrayList<Cart> list) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("cart.db"))));
			oos.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("카트정보 저장 오류");
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}// end of saveCustomerList

	// cart 정보 로딩
	public static ArrayList<Cart> cartLoading() {
		ArrayList<Cart> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("cart.db"))));
			list = (ArrayList<Cart>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("카트 정보 로딩오류");
		} catch (Exception e) {
			System.out.println("카트 정보 로딩오류");
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}// end of customerRoading

	// 카트 마지막 저장
	public static void cartLastSave(Cart cart, ArrayList<Cart> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(customer.getId()) && list.get(i).getPw() == customer.getPassword()) {
				list.get(i).setList(cart.getList());
				list.get(i).setPaymentItemList(cart.getPaymentItemList());
				saveCartList(list);
			}
		} // end of for
	}// end of cartLastSave

	// 카트 확인 함수
	public static Cart cartCheck() {
		Cart cart = new Cart();
		if (cartList.isEmpty()) {
			return cart;
		}
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getId().equals(customer.getId()) && cartList.get(i).getPw() == customer.getPassword()) {
				cart = cartList.get(i);
				break;
			}
		}
		return cart;
	}

	// 고객 메뉴
	private static int customerMenu() {
		String regExp = "^[0-9]+$";
		String strNum = null;
		boolean flag = false;
		int num = 0;
		while (!flag) {
			System.out.println("***********************************************************");
			System.out.println("*\t\t  " + "Performance Market Menu");

			System.out.println("***********************************************************");
			System.out.println("*  1. 내 정보 확인하기  \t\t6. 예매내역 전체삭제하기");
			System.out.println("*  2. 공연 정보 목록 보기\t\t7. 결제하기");
			System.out.println("*  3. 공연 예매하기    \t\t8. 결제내역 보기");
			System.out.println("*  4. 예매내역 목록 보기 \t\t9. 로그아웃");
			System.out.println("*  5. 예매내역 항목 삭제 \t\t10.고객센터 ");
			System.out.println("***********************************************************");
			System.out.print("*메뉴 번호를 선택해주세요: ");
			strNum = sc.nextLine();
			if (strNum.matches(regExp)) {
				num = Integer.parseInt(strNum);
				if (num > 0 && num <= 10) {
					flag = true;
				} else {
					System.out.println("1~10까지 입력하세요!");
				}
			} else {
				System.out.println("잘못입력하셨습니다. ");
			}
		} // end of while
		return Integer.parseInt(strNum);
	}// end of customerMenu

	// 내정보 확인
	public static void myInformation() {
		System.out.println("──────────── 내 정 보 ────────────");
		System.out.println(" 이름: " + customer.getName());
		System.out.println(" 나이: " + customer.getAge());
		System.out.println(" 전화번호: " + customer.getPhone());
		System.out.println(" 주소: " + customer.getAddress());
		System.out.println(" 등급: " + customer.getGrade());
		System.out.println(" 적립금: " + customer.getMileage());
		System.out.println();
	}// end of myInformation

	// 공연정보 검색
	public static void searchPerformance() {
		ArrayList<Performance> pList = performanceList;
		String regExp = "^[0-9]+$";
		String strNum = null;
		boolean flag = false;
		int num = 0;
		while (!flag) {
			System.out.println("***********************************************************");
			System.out.println("1.모든 공연 보기 2.검색 3.선택 정렬 4. 예매하기 5.뒤로 가기 ");
			System.out.println("***********************************************************");
			System.out.print("*번호를 입력하세요: ");
			strNum = sc.nextLine();
			if (strNum.matches(regExp)) {
				num = Integer.parseInt(strNum);
				if (num > 0 && num <= 5) {
					switch (num) {
					case 1:
						cart.printPerformanceList(pList);
						break;
					case 2:
						findPerformance(pList);
						break;
					case 3:
						sortedPerformance(pList);
						break;
					case 4:
						performanceReservation();
						break;
					case 5:
						flag = true;
						break;
					}
				} else {
					System.out.println("1~5까지 입력하세요!");
				}
			} else {
				System.out.println("잘못입력하셨습니다. ");
			}
		} // end of while
	}// end of serchPerformance

	// 공연 검색
	public static void findPerformance(ArrayList<Performance> pList) {
		boolean flag = false;
		System.out.print("검색 정보 입력:(공연ID,공연명,장소): ");
		String info = sc.nextLine();

		for (Performance data : pList) {
			if (data.getName().contains(info) || data.getGenre().contains(info) || data.getVenue().contains(info)) {
				cart.printPerformance(data);
				flag = true;
			}
		} // end of for
		if (!flag) {
			System.out.println("찾으시는 공연이 없습니다.");
		}
	}// end of finPerformance

	// 선택 정렬 기능
	public static void sortedPerformance(ArrayList<Performance> pList) {
		boolean flag = false;
		String regExp = "^[0-9]+$";
		String strNum = null;
		int num = 0;
		ArrayList<Performance> subList = pList;

		while (!flag) {
			System.out.println("***********************************************************");
			System.out.println("* 1.오름차순  2.내림차순  3.가격높은순  4.가격낮은순  5.판매량순 6.뒤로가기");
			System.out.println("***********************************************************");
			System.out.print("*번호를 입력하세요: ");
			strNum = sc.nextLine();
			if (strNum.matches(regExp)) {
				num = Integer.parseInt(strNum);
				if (num > 0 && num <= 6) {
					switch (num) {
					case 1:
						Collections.sort(subList);
						cart.printPerformanceList(subList);
						break;
					case 2:
						Collections.sort(subList.reversed());
						cart.printPerformanceList(subList);
						break;
					case 3:
						Collections.sort(subList, (p1, p2) -> {
							int price1 = p1.getTicketPrice();
							int price2 = p2.getTicketPrice();
							if (price1 == price2) {
								return p1.getPerformanceID().compareToIgnoreCase(p2.getPerformanceID());
							}
							return Integer.compare(price2, price1);
						});
						cart.printPerformanceList(subList);
						break;
					case 4:
						Collections.sort(subList, (p1, p2) -> {
							int price1 = p1.getTicketPrice();
							int price2 = p2.getTicketPrice();
							if (price1 == price2) {
								return p1.getPerformanceID().compareToIgnoreCase(p2.getPerformanceID());
							}
							return Integer.compare(price1, price2);
						});
						cart.printPerformanceList(subList);
						break;
					case 5:
						Collections.sort(subList, (p1, p2) -> {
							int seat1 = p1.getSoldSeats();
							int seat2 = p2.getSoldSeats();
							if (seat1 == seat2) {
								return p1.getPerformanceID().compareToIgnoreCase(p2.getPerformanceID());
							}
							return seat2 - seat1;
						});
						cart.printPerformanceList(subList);
						break;
					case 6:
						flag = true;
						break;
					}
				} else {
					System.out.println("1~6까지 입력하세요!");
				}
			} else {
				System.out.println("잘못입력하셨습니다. ");
			}
		} // end of while
	}// end of sortedPerformance

	// 에매하기
	public static void performanceReservation() {
		cart.printPerformanceList(performanceList);
		int count = 0;

		boolean exitFlag = false;
		while (!exitFlag) {
			System.out.print("예매하실 공연의 ID를 입력하세요(뒤로가기 exit): ");
			String string = sc.nextLine();
			String str = string.toLowerCase();
			if (str.toLowerCase().equals("exit")) {
				exitFlag = true;
			} else {
				boolean flag = false;
				boolean checkFlag = false;
				int id = -1;
				for (int i = 0; i < performanceList.size(); i++) {
					if (string.equals(performanceList.get(i).getPerformanceID())) {
						checkFlag = true;
						if (performanceList.get(i).getLimitAge() > customer.getAge()) {
							System.out.println("선택하신 공연의 관람연령은 " + performanceList.get(i).getLimitAge() + "세 입니다");
							System.out.println("예매하실 수 없습니다.");
							continue;
						}
						if (performanceList.get(i).getSoldSeats() == Integer 
								.parseInt(performanceList.get(i).getTotalSeats())) {
							System.out.println("선택하신 공연은 매진입니다.");
							continue;

						} else {
							id = i;
							flag = true;
							break;
						}
					}
				}
				if (!checkFlag) {
					System.out.println("!!찾으시는 공연이 없습니다!!");
					continue;
				}
				if (flag) {
					boolean flag1 = false;
					String regExp = "^[0-9]+$";
					String strNum = null;
					int num = 0;
					System.out.print("예매하실 수량을 입력해주세요: ");
					strNum = sc.nextLine();
					if (strNum.matches(regExp)) {
						num = Integer.parseInt(strNum);
						int x = 0;
						int y = 0;
						ArrayList<String> slist = new ArrayList<String>();
						cart.printPerformanceSeat(performanceList.get(id));
						for (int k = 0; k < num; k++) {
							try {
								System.out.print("좌석을 선택하세요: ");
								String str1 = sc.nextLine();
								String str2 = str1.toUpperCase();
								String s1 = str1.substring(1);
								x = (str2.charAt(0)) - 65;
								y = (Integer.parseInt(s1)) - 1;
								if (performanceList.get(id).getSeat()[x][y] == 1) {
									System.out.println("선택하신 좌석은 이미 예약되어 있습니다.");
									System.out.println("다른 좌석을 선택해주세요!");
									num += 1;
									continue;
								}
								performanceList.get(id).getSeat()[x][y] = 1;
								count++;
								slist.add(str2);
							} catch (Exception e) {
								System.out.println("잘못입력하셨습니다 다시 입력해주세요!");
								num += 1;
								continue;
							}
						}
						if (!flag1) {
							cart.printPerformanceSeat(performanceList.get(id));
							String seat = "";
							for (String data : slist) {
								seat += data + ",";
							}
							performanceList.get(id).setSoldSeats(performanceList.get(id).getSoldSeats() + count);
							System.out.println(performanceList.get(id).getName() + " 공연이 추가되었습니다");
							if (!cart.isCartInPerformance(performanceList.get(id).getPerformanceID(), count, seat)) {
								cart.insertPerformance(performanceList.get(id), count, seat);
							}
						}
						exitFlag = true;
					} else {
						System.out.println("잘못입력하셨습니다. ");
					}
				}
			}
		} // end of while
	}// end of performanceReservation

	// 장바구니 목록 출력
	public static void printCartItem() {
		if (cart.getList().size() == 0) {
			System.out.println("─────────예매하신 공연이 없습니다─────────");
		} else {
			cart.printCart();
		}
	}// end of printCartItem

	// 예매내역 모든 항목 지우기
	public static void deleteAllCartItem() {
		if (cart.getList().size() == 0) {
			System.out.println("─────────예매하신 공연이 없습니다─────────");
		} else {
			System.out.println("예매하신 공연 모두 삭제하겠습니까? Y | N ");
			String str = sc.nextLine();
			if (str.toUpperCase().equals("Y") || str.toLowerCase().equals("y")) {
				System.out.println("예매하신 공연 모두 삭제되었습니다.");
				try {
					cart.deletePerformance(performanceList);
					savePerformanceList(performanceList);
					performanceList = performanceLoading();
				} catch (Exception e) {
					System.out.println("삭제함수 오류 2");
				}

			}
		}
	}// end of deleteCartItem

	// 선택한 공연 삭제
	public static void cartRemoveItem() {
		if (cart.getList().size() == 0) {
			System.out.println("─────────예매하신 공연이 없습니다─────────");
		} else {
			cart.printCart();
			boolean exitFlag = false;

			while (!exitFlag) {
				System.out.print("삭제하실 공연ID를 입력하세요(뒤로가기 exit): ");
				String str = sc.nextLine();
				if (str.toLowerCase().equals("exit")) {
					exitFlag = true;
				} else {
					boolean flag = false;
					int num = -1;

					for (int i = 0; i < cart.getList().size(); i++) {
						if (str.equals(cart.getList().get(i).getPerformanceID())) {
							num = i;
							flag = true;
							break;
						}
					}
					if (flag) {
						System.out.println("정말 삭제하시겠습니까? Y | N");
						str = sc.nextLine();
						if (str.toUpperCase().equals("Y") || str.toLowerCase().equals("y")) {
							System.out.println(cart.getList().get(num).getPerformanceName() + "공연이 삭제되었습니다.");
							for (int j = 0; j < performanceList.size(); j++) {
								if (performanceList.get(j).getPerformanceID()
										.equals(cart.getList().get(num).getPerformanceID())) {
									performanceList.get(j).setSoldSeats((performanceList.get(j).getSoldSeats())
											- (cart.getList().get(num).getQuantity()));
								}
							}
							try {
								cart.removeCart(num);
								savePerformanceList(performanceList);
								performanceList = performanceLoading();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						exitFlag = true;
					} else {
						System.out.println("잘못 입력하셨습니다");
					}
				}
			} // end of while
		}
	}// end of cartRemoveItem

	private static void cartPaying() {
		int sum = 0;
		int sale = 0;
		int point = 0;
		int sum1 = 0;
		if (cart.getList().size() == 0) {
			System.out.println("─────────예매하신 공연이 없습니다─────────");
		} else {
			cart.printCart();
			System.out.println("결제를 하시겠습니까? Y | N");
			String str = sc.nextLine();
			if (str.toUpperCase().equals("Y") || str.toLowerCase().equals("y")) {
				for (int i = 0; i < cart.getList().size(); i++) {
					System.out.println(cart.getList().get(i).getPerformanceName() + " 공연이 결제되었습니다");
					sale += (int) (cart.getList().get(i).getTotalPrice() * customer.getSaleRatio());
					point += (int) cart.getList().get(i).getTotalPrice() * customer.getMileageSale();
					sum += (cart.getList().get(i).getTotalPrice()) - (sale);
					cart.getPaymentItemList().add(cart.getList().get(i));

				}
				for (int i = 0; i < cart.getPaymentItemList().size(); i++) {
					sum1 += cart.getPaymentItemList().get(i).getTotalPrice();
				}
				customer.setAccumulatedPayment(sum1);
				customer.setMileage(customer.getMileage() + point);
				customerList = cart.changeCutomer(customerList, customer);
				try {
					saveCustomerList(customerList);
					customerList = customerLoading();
					cart.getList().clear();
					savePerformanceList(performanceList);
					performanceList = performanceLoading();
					for (int i = 0; i < customerList.size(); i++) {
						if (customer.getId().equals(customerList.get(i).getId())) {
							customer = customerList.get(i);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("\t\t주문 총금액 : " + sum + "원\n");
				System.out.println("\t\t할인된 금액 : " + sale + "원\n");
				System.out.println("\t\t적립된 포인트 : " + point + "점\n");
				System.out.println("-----------------------------------------------");
				System.out.println();
			} // end of if
		}
	}// end of cartPaying

	// 결제내역 출력
	public static void printPaymentList() {
		if (cart.getPaymentItemList().size() == 0) {
			System.out.println("─────────결제하신 공연이 없습니다─────────");
		} else {
			cart.printPaymentCart();
		}
	}

	// 고객센터 정보 로딩
	public static ArrayList<Service> serviceLoading() {
		ArrayList<Service> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("contend.db"))));
			list = (ArrayList<Service>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로딩 오류");
		}
		return list;
	}

	// 고객센터 연결
	public static void questionsService(ArrayList<Service> list) throws UnknownHostException, IOException {

		Socket socket = new Socket("192.168.219.100", 14276);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		boolean flag = false;
		while (!flag) {
			try {
				boolean flag1 = false;
				boolean flag2 = false;
				System.out.println("              자주 하는 질문 키워드 ");
				System.out.println("────────────────────────────────────────────");
				System.out.println();

				System.out.print("[ " + list.get(0).getCategory() + " ]" + "  ");
				for (int i = 1; i < list.size(); i++) {
					if (list.get(i).getCategory().contains(list.get(i - 1).getCategory())) {
					} else {
						System.out.print("[ " + list.get(i).getCategory() + " ]" + "  ");
					}
				}
				System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

				String str = dis.readUTF();
				System.out.println(str);
				String s = sc.nextLine();
				dos.writeUTF(s);
				while (!flag1) {
					String str1 = dis.readUTF();
					System.out.println(str1);
					if (str1.equals("찾으시는 키워드가 없습니다.")) {
						flag2 = true;
					}
					if (str1.equals("..")) {
						flag1 = true;
						break;
					}
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				} // end of while
				if (flag2) {
					continue;
				}
				System.out.print("원하는 질문번호를 입력하세요: ");
				String s1 = sc.nextLine();
				dos.writeUTF(s1);
				String str3 = dis.readUTF();
				System.out.println(str3);
				String str4 = dis.readUTF();
				System.out.println(str4);

				System.out.println("추가 질문 있으십니까 Y | N");
				String str2 = sc.nextLine();
				dos.writeUTF(str2.toUpperCase());
				if (str2.toUpperCase().equals("N")) {
					flag = true;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // end of while
		try {
			dis.close();
			dos.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("고객센터를 종료합니다.");
	}// end of conectService

	public static void connectService(ArrayList<Service> list) {
		boolean flag = false;
		String strNum = null;
		String regExp = "^[0-9]+$";
		int num = 0;
		try {
			while (!flag) {
				System.out.println("┌────────────────────────────────────────────┐");
				System.out.println(" ***************** 고 객 센 터 ****************");
				System.out.println(" * 1.자주하는 질문     2.1대1 문의       3.종료   *");
				System.out.println(" ********************************************");
				System.out.println("└────────────────────────────────────────────┘");
				System.out.print("*번호를 선택해 주세요: ");

				strNum = sc.nextLine();
				if (strNum.matches(regExp)) {
					num = Integer.parseInt(strNum);
					if (num > 0 && num <= 3) {
						switch (num) {

						case 1:
							questionsService(list);
							break;
						case 2:
							inquiryService();
						case 3:
							flag = true;
							break;
						}
					} else {
						System.out.println("1~3까지 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			System.out.println("고객 센터 오류");
		}

	}// end of connectService

	// ID/PW 찾기
	public static void findCustomerInfo(ArrayList<Customer> list) {
		boolean flag = false;
		System.out.println("*********************");
		System.out.println("*   ID / PW 찾 기    *");
		System.out.println("*********************");
		System.out.print("이름: ");
		String name = sc.nextLine();
		System.out.print("전화번호: ");
		String phone = sc.nextLine();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(name) && list.get(i).getPhone().equals(phone)) {
				System.out.println("이름:" + list.get(i).getName() + " 전화번호:" + list.get(i).getPhone());
				System.out.println("ID: " + list.get(i).getId());
				System.out.println("PW: " + list.get(i).getPassword());
				flag = true;
				break;
			}
		}
		if (!flag) {
			System.out.println("입력하신 회원이 없습니다.");
		}
	}// end of findCustomerInfo

	// 관리자 정보 로드
	public static void adminLoading() {
		String[] s = new String[2];

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("admin.db")));
			s[0] = br.readLine();
			s[1] = br.readLine();
		} catch (Exception e) {
			System.out.println("관리자 정보 로딩 오류");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		admin.setAdminID(s[0]);
		admin.setAdminPW(Integer.parseInt(s[1]));
	}

	public static void inquiryService() {
		String filePath ="D:/git/ticket-repository/BigBigProject/src/controller/connect.properties";
		Properties properties = new Properties();
		boolean flag = false;
		try {
			properties.load(new FileReader(filePath));
			String ip = properties.getProperty("ip");
			String port = properties.getProperty("port");
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
							if(data.contains("안녕히 가십시오.")) {
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
			while(!flag) {				
				String message =sc.nextLine();
				if(message.equals("exit")) {
					flag = true;
					continue;
				}
				dos.writeUTF(customer.getName()+message);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}// end of class
