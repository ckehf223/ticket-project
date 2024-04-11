package project.cart;

import java.io.Serializable;
import java.util.ArrayList;

import project.member.Customer;
import project.member.GoldCustomer;
import project.member.VIPCustomer;
import project.performance.Performance;

public class Cart implements CartInterface,Serializable{
	public static final int VIP_CUT = 800_000;
	public static final int GOLD_CUT = 400_000;
	private ArrayList<CartItem> list = new ArrayList<>();
	private ArrayList<CartItem> paymentItemList = new ArrayList<>();//장바구니 결제 후 넘길 리스트, 영수증
	private String id;
	private int password;
	
	public Cart() {
		
	}
	
	public Cart(String id, int password) {
		this.id = id;
		this.password = password;
	}
	
	//좌석 정보를 보여주는 함수
	public  void printPerformanceSeat(Performance p) {
		int ch ='A';
		System.out.println("**********************");
		System.out.println("*********무 대*********");
		System.out.println("**********************");
		System.out.println();
		System.out.print("  "+"1 2 3 4 5 6 7 8 9 10\n");
		for(int i=0;i<p.getSeat().length;i++) {
			System.out.print((char)(ch+i)+" ");
			for(int j=0;j<p.getSeat()[i].length;j++	) {
				System.out.print(p.getSeat()[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	
	@Override
	public  void printPerformanceList(ArrayList<Performance> pList) {
		System.out.println("공연ID| 공연타입 | 공연명 |  공연장소  |  공연일  |관람연령|  가 격  |  좌 석  | ");
		for(int i=0;i<pList.size();i++) {
			Performance performance = pList.get(i);
			System.out.print("  "+performance.getPerformanceID()+" | ");
			System.out.print(performance.getGenre()+" | ");
			System.out.print(performance.getName()+" | ");
			System.out.print(performance.getVenue()+" | ");
			System.out.print(performance.getDayOfPerformance()+" | ");
			System.out.print(performance.getLimitAge()+"세 | ");
			System.out.print(performance.getTicketPrice()+" | ");
			System.out.print(performance.getSoldSeats()+"/"+performance.getTotalSeats()+" | ");
			System.out.println();
		}
		
	}
	@Override
	public boolean isCartInPerformance(String pID,int num,String seat) {
		boolean flag = false;
		for(int i=0;i<list.size();i++) {
			if(pID.equals(list.get(i).getPerformanceID())) {
				list.get(i).setQuantity(list.get(i).getQuantity()+num);
				list.get(i).setSeatNum(list.get(i).getSeatNum()+seat);
				flag = true;
			}
		}
		return flag;
	}
	@Override
	public void insertPerformance(Performance p,int num,String seat) {
		CartItem pItem = new CartItem(p);
		pItem.setQuantity(num);
		pItem.setSeatNum(seat);
		list.add(pItem);
	}
	
	@Override
	public void removeCart(int numId) {
		int x = 0;
		int y = 0;
		String dstr = list.get(numId).getSeatNum();
		String[] str = dstr.split(",");
		for(int i=0;i<str.length;i++) {
			String s = str[i].substring(1);
			x = (str[i].charAt(0))-65;
			y = (Integer.parseInt(s))-1;
			list.get(numId).getItem().getSeat()[x][y] = "□";
		}
		list.remove(numId);
	}
	
	@Override
	public void deletePerformance(ArrayList<Performance> list1) {
		int x =0;
		int y =0;
		int sum =0;
		try {
			for(int i=0;i<list.size();i++) {
				String dStr = "";
				dStr = list.get(i).getSeatNum();
				String[] str = dStr.split(",");
				for(int k=0;k<list.get(i).getQuantity();k++){
					String s = str[k].substring(1);
					x = (str[k].charAt(0))-65;
					y = (Integer.parseInt(s))-1;
					list.get(i).getItem().getSeat()[x][y] ="□";
				}
				for(int j=0;j<list1.size();j++) {
					if(list.get(i).getItem().getPerformanceID().equals(list1.get(j).getPerformanceID())) {
						list1.get(j).setSoldSeats((list1.get(j).getSoldSeats())-(list.get(i).getQuantity()));
					}
				}
			}
			list.clear();
		}catch(Exception e) {
			System.out.println("삭제함수 오류");
			e.printStackTrace();
		}
	}
	public void seatDelete(Performance p , String seat) {
		
	}
	
	public void printCart() {
		System.out.println("예매내역 보기 ");
		System.out.println("---------------------------------------------------------");
		System.out.println(" 공연ID|  공연명  |   공연일   |    장소   |  좌석번호 |  수량 |  합계");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print("   " + list.get(i).getPerformanceID()+ " |");
			System.out.print(" " + list.get(i).getPerformanceName()+ " |");
			System.out.print("  " + list.get(i).getPerformanceDay()+ "  |");
			System.out.print("  " + list.get(i).getPerformanceVenue()+ " |");
			System.out.print("  " + list.get(i).getSeatNum() + " |");
			System.out.print("  " + list.get(i).getQuantity() + " |");
			System.out.print("  " + list.get(i).getTotalPrice());
			System.out.println("  ");
		}
		System.out.println("---------------------------------------------------------");
	}
	
	public void printPaymentCart() {
		System.out.println("결제내역  보기 ");
		System.out.println("---------------------------------------------------------");
		System.out.println("공연ID |  공연명  |   공연일    |   장소   |  좌석 번호 |  수량");
		
		for (int i = 0; i < paymentItemList.size(); i++) {
			System.out.print("  " + paymentItemList.get(i).getPerformanceID()+ " |");
			System.out.print("  " + paymentItemList.get(i).getPerformanceName()+ " |");
			System.out.print("  " + paymentItemList.get(i).getPerformanceDay()+ "   |");
			System.out.print("  " + paymentItemList.get(i).getPerformanceVenue()+ " |");
			System.out.print("  " + paymentItemList.get(i).getSeatNum() + " |");
			System.out.print("  " + paymentItemList.get(i).getQuantity());
			System.out.println("  ");
		}
		System.out.println("---------------------------------------------------------");
		
	}
	
	
	
	//자동 승급 기능
	public  ArrayList<Customer> changeCutomer(ArrayList<Customer> list , Customer cus){
		boolean flag = false;
		if(cus.getAccumulatedPayment() > GOLD_CUT && cus.getAccumulatedPayment() < VIP_CUT) {
			GoldCustomer g = new GoldCustomer(cus);
			list.add(g);
			flag = true;
		}
		if(cus.getAccumulatedPayment() >VIP_CUT) {
			VIPCustomer v = new VIPCustomer(cus);
			list.add(v);
			flag = true;
		}
		if(flag) {
			for(int i=0;i<list.size();i++) {
				if(list.get(i).equals(cus)) {
					list.remove(i);
				}
			}
		}
		if(flag == false) {
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getId().equals(cus.getId())) {
					list.get(i).setId(cus.getId());
					list.get(i).setPassword(cus.getPassword());
					list.get(i).setPhone(cus.getPhone());
					list.get(i).setAge(cus.getAge());
					list.get(i).setGrade(cus.getGrade());
					list.get(i).setAddress(cus.getAddress());
					list.get(i).setName(cus.getName());
					list.get(i).setMileage(cus.getMileage());
					list.get(i).setMileageSale(cus.getMileageSale());
					list.get(i).setAccumulatedPayment(cus.getAccumulatedPayment());
					list.get(i).setSaleRatio(cus.getSaleRatio());
				}
			}
		}
		return list;
	}
	
	
	
	
	public ArrayList<CartItem> getList() {
		return list;
	}
	public void setList(ArrayList<CartItem> list) {
		this.list = list;
	}
	public ArrayList<CartItem> getPaymentItemList() {
		return paymentItemList;
	}
	public void setPaymentItemList(ArrayList<CartItem> paymentItemList) {
		this.paymentItemList = paymentItemList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPw() {
		return password;
	}
	public void setPw(int password) {
		this.password = password;
	}
	
	
	
}
