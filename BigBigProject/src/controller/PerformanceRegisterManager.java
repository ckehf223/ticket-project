package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.PerformanceVO;

public class PerformanceRegisterManager {
	public static Scanner sc = new Scanner(System.in);

	// 공연 등록
	public static void performanceRegister() throws Exception {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
		String pf_id = "P" + formatter.format(date);

		System.out.print("공연ID: " + pf_id);
		System.out.println();
		System.out.print("공연명: ");
		String pf_name = sc.nextLine();
		System.out.print("장르: ");
		String pf_genre = sc.nextLine();
		System.out.print("공연일(YYYY/MM/DD): ");
		String pf_date = sc.nextLine();
		System.out.print("장소: ");
		String pf_venue = sc.nextLine();
		System.out.print("관람연령(숫자만 입력) : ");
		String pf_limitAge = sc.nextLine();
		System.out.print("좌석수 : ");
		String pf_totalseats = sc.nextLine();
		System.out.print("가격(숫자): ");
		String pr_price = sc.nextLine();

		PerformanceVO pvo = new PerformanceVO(pf_id, pf_name, pf_genre, pf_date, pf_venue,
				Integer.parseInt(pf_limitAge), pf_totalseats, Integer.parseInt(pr_price));
		PerformanceDAO.setPerformanceRegister(pvo);
	}// end of performanceRegister

	// 공연 삭제
	public static void performanceDelete() throws Exception {
		System.out.print("삭제할 공연 ID: ");
		String id = sc.nextLine();
		boolean success = PerformanceDAO.getPerformanceSearch(id);
		if (success) {
			System.out.println("정말 삭제 하시겠습니까? Y|N");
			String yesNo = sc.nextLine();
			if (yesNo.toUpperCase().equals("Y")) {
				PerformanceDAO.setDeletePerformance(id);
			}
		} else {
			System.out.println("입력하신 공연이 없습니다. 다시 확인해 주세요");
		}
	}// end of performanceDelete()

}
