package controller;

import java.util.Scanner;

import model.CustomerVO;

public class CartPaymentRegisterManager {
	
	//결제하기
	public static void cartPayment(CustomerVO cvo) throws Exception{
		Scanner sc = new Scanner(System.in);
		int totalPrice =0;
		int mileage = 0;
		int salePrice=0;
		System.out.println("결제 하시겠습니까? Y | N");
		String yesNo = sc.nextLine();
		if(yesNo.toUpperCase().equals("Y")) {
			totalPrice = CartPaymentDAO.getCartTotalPrice(cvo.getCt_id());
			salePrice =  (int)(totalPrice*cvo.getCt_saleRatio());
			mileage = (int)(totalPrice*cvo.getCt_mileageSale());
			cvo.setCt_totalamount(cvo.getCt_totalamount()+(totalPrice-salePrice));
			cvo.setCt_mileage(cvo.getCt_mileage()+mileage);
			if(cvo.getCt_totalamount() < 800_000 && cvo.getCt_totalamount() < 400_000) {
				cvo.setCt_grade("GOLD");
				cvo.setCt_saleRatio(0.06);
				cvo.setCt_mileageSale(0.06);
			}else if(cvo.getCt_totalamount() > 800_000) {
				cvo.setCt_grade("VIP");
				cvo.setCt_saleRatio(0.08);
				cvo.setCt_mileageSale(0.1);
			}
			CartPaymentDAO.getCartPayment(cvo.getCt_id());
			CartPaymentDAO.setCustomerChanger(cvo);
		}else {
			
		}
	}
}
