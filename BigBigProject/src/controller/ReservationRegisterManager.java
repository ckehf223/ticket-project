package controller;

import java.util.Scanner;

import model.CartVO;
import model.CustomerVO;
import model.PerformanceVO;

public class ReservationRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 공연 예매
	public static void reservationPerformance(String ct_id, String pw) throws Exception {
		boolean pf_check = false;
		String pf_id = null;
		int count = 0;
		int limitAge = 0;
		String seats = "";
		String seat = "";
		boolean seatCheck = false;
		CustomerVO cvo = null;
		PerformanceVO pvo = null;

		while (!pf_check) {
			System.out.print("예매할 공연 ID: ");
			pf_id = sc.nextLine();
			pf_check = PerformanceDAO.getPerformanceSearch(pf_id); // 공연 존재 여부
			if (!pf_check) {
				System.out.println("잘못입력 하셨습니다. 다시 입력 해주세요.");
			}
		} // end of while

		cvo = CustomerDAO.getCustomerLogin(ct_id, pw); // 유저정보
		limitAge = ReservationDAO.getCheckLimitAge(pf_id); // 관람연령
		pvo = ReservationDAO.getPerformanceVO(pf_id); // 공연정보

		if (cvo.getCt_age() >= limitAge) { // 관람연령체크
			System.out.print("예매하실 수량: ");
			count = sc.nextInt();
			sc.nextLine(); // 입력 버퍼 클리어
			SeatRegisterManager.printPerformanceSeat(pf_id); // 좌석 출력

			for (int i = 0; i < count; i++) {
				System.out.print("좌석을 선택하세요: ");
				seat = sc.nextLine().toUpperCase();
				seatCheck = SeatDAO.getPerformanceSeatCheck(pf_id, seat); // 좌석 중복체크
				if (!seatCheck) {
					System.out.println("선택하신 좌석은 이미 예약되어 있습니다.");
					System.out.println("다른 좌석을 선택해주세요!");
					i += -1;
					continue;
				} else {
					seats += seat + ",";
				}
			} // end of for문
			CartVO cartvo = new CartVO();
			cartvo.setCt_id(ct_id);
			cartvo.setCart_quantity(count);
			cartvo.setPf_id(pf_id);
			cartvo.setCart_totalPrice(pvo.getPf_price() * count);
			cartvo.setSeat_location(seats);
			cartvo.setPayment_check(0);

			ReservationDAO.getReservationPerformance(cartvo);
		} else {
			System.out.println("선택하신 공연의 관람연령은 " + limitAge + "세 입니다");
			System.out.println("예매하실 수 없습니다.");
		}
		SeatRegisterManager.printPerformanceSeat(pf_id);
	}// end of reservationPerformance
}
