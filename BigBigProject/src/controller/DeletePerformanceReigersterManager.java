package controller;

import java.util.Scanner;

public class DeletePerformanceReigersterManager {
	public static Scanner sc = new Scanner(System.in);
	//예매내역 전체 삭제
	public static void cartDeletePerformance(String ct_id) throws Exception {
		System.out.println("예매내역을 삭제 하시겠습니까? Y | N");
		String yesNo = sc.nextLine();
		if(yesNo.toUpperCase().equals("Y")) {
			DeletePerformanceDAO.getSeatDelete(ct_id);
			DeletePerformanceDAO.setCartPerformanceDelete(ct_id);
		}
	}
}
