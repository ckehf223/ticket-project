package controller;

import java.util.Scanner;

import model.CustomerVO;

public class CartPaymentRegisterManager {
	
	//결제하기
	public static void cartPayment(CustomerVO cvo) throws Exception{
		Scanner input = new Scanner(System.in);
		int totalPrice =0;
		int mileage = 0;
		int salePrice=0;
		int point = 0;
		System.out.println("결제 하시겠습니까? Y | N");
		String yesNo = input.nextLine();
		if(yesNo.toUpperCase().equals("Y")) {
			if(cvo.getCt_mileage() > 5000) {
				System.out.println("적립금 사용가능(최소 5000)사용하시겠습니까? Y | N");
				String useMileage = input.nextLine();
				if(useMileage.toUpperCase().equals("Y")) {
					while(true) {
						System.out.println("보유포인트: "+cvo.getCt_mileage());
						System.out.print("사용할 포인트 입력: ");
						point = input.nextInt();
						input.nextLine();
						if(point <= cvo.getCt_mileage() && point >= 5000) {
							cvo.setCt_mileage(cvo.getCt_mileage()-point);
							break;
						}else {
							System.out.println("포인트가 부족합니다 다시 입력해주세요");
							continue;
						}
					}
				}
			}
			totalPrice = CartPaymentDAO.getCartTotalPrice(cvo.getCt_id());
			System.out.println(totalPrice);
			System.out.println(point);
			totalPrice = totalPrice - point;
			System.out.println(totalPrice);
			salePrice =  (int)(totalPrice*cvo.getCt_saleRatio());
			mileage = (int)(totalPrice*cvo.getCt_mileageSale());
			cvo.setCt_totalamount(cvo.getCt_totalamount()+(totalPrice-salePrice));
			cvo.setCt_mileage(cvo.getCt_mileage()+mileage);
			
			if(cvo.getCt_totalamount() < 800_000 && cvo.getCt_totalamount() > 400_000) {
				cvo.setCt_grade("GOLD");
				cvo.setCt_saleRatio(0.06);
				cvo.setCt_mileageSale(0.06);
			}else if(cvo.getCt_totalamount() > 800_000) {
				cvo.setCt_grade("VIP");
				cvo.setCt_saleRatio(0.08);
				cvo.setCt_mileageSale(0.1);
			}else {
				
			}
			CartPaymentDAO.getCartPayment(cvo.getCt_id());
			CartPaymentDAO.setCustomerChanger(cvo);
		}else {
			
		}
	}
}
