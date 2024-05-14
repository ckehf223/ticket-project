package controller;

import model.PerformanceVO;

public class SeatRegisterManager {

	// 예매하기 공연 좌석출력
	public static void printPerformanceSeat(String pf_id) throws Exception {
		PerformanceVO pvo = SeatDAO.getPerformanceSeats(pf_id);

		int ch = 'A';
		System.out.println("***********************************************************");
		System.out.println("*               공      연       무       대                 *");
		System.out.println("***********************************************************");
		System.out.println();
		System.out.print("    " + "  1    2    3    4    5    6    7    8    9    10\n");
		for (int i = 0; i < pvo.getSeat().length; i++) {
			System.out.print(" " + (char) (ch + i));
			for (int j = 0; j < pvo.getSeat()[i].length; j++) {
				if (pvo.getSeat()[i][j] == 1) {
					System.out.print("    " + "■");
				}
				if (pvo.getSeat()[i][j] == 0) {
					System.out.print("    " + "□");
				}
			}
			System.out.println("");
			System.out.println("     ==================================================");
		}
	}
}
