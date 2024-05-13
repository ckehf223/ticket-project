package project.cart;

import java.io.Serializable;
import java.util.ArrayList;

import project.member.Customer;
import project.member.GoldCustomer;
import project.member.VIPCustomer;
import project.performance.Performance;

public class Cart implements CartInterface, Serializable {
	public static final int VIP_CUT = 800_000;
	public static final int GOLD_CUT = 800_000;
	private ArrayList<CartItem> list = new ArrayList<>();
	private ArrayList<CartItem> paymentItemList = new ArrayList<>();// 장바구니 결제 후 넘길 리스트, 영수증
	private String id;
	private int password;

	public Cart() {

	}

	public Cart(String id, int password) {
		this.id = id;
		this.password = password;
	}

	// 좌석 정보를 보여주는 함수
	public void printPerformanceSeat(Performance p) {
		int ch = 'A';
		System.out.println("***********************************************************");
		System.out.println("*               공      연       무       대                 *");
		System.out.println("***********************************************************");
		System.out.println();
		System.out.print("    " + "  1    2    3    4    5    6    7    8    9    10\n");
		for (int i = 0; i < p.getSeat().length; i++) {
			System.out.print(" " + (char) (ch + i));
			for (int j = 0; j < p.getSeat()[i].length; j++) {
				if(p.getSeat()[i][j] == 1) {
					System.out.print("    "+"■");
				}
				if(p.getSeat()[i][j] == 0) {
					System.out.print("    "+"□");
				}
//				System.out.print("    " + p.getSeat()[i][j]);
			}
			System.out.println("");
			System.out.println("     ==================================================");
		}
	}

	public void printPerformance(Performance p) {
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("   공 연 ID    │ 공연  장르  │   공연명    │  공연일  │  공연 장소   │관람연령│  가 격  │  좌 석   ");
		System.out.println("────────────────────────────────────────────────────────────────────────────────────");

		System.out.printf("%-16s", p.getPerformanceID());
		System.out.printf("%-9s", "  " + p.getGenre());
		System.out.printf("%-11s", "  " + p.getName());
		System.out.printf("%-10s", " " + p.getDayOfPerformance());
		System.out.printf("%-10s", "  " + p.getVenue());
		System.out.printf("%-7s", "   " + p.getLimitAge()+"세");
		System.out.printf("%-7s", "  " + p.getTicketPrice());
		System.out.printf("%-8s", " " + p.getSoldSeats() + "/" + p.getTotalSeats());
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------");

	}

	@Override
	public void printPerformanceList(ArrayList<Performance> pList) {
//		System.out.println("   공 연 ID    │ 공연 장르 │ 공 연 명  │  공연 장소   │ 공 연 일 │관람연령│  가 격  │ 좌 석 ");
		System.out.printf("%-13s│", "   공연 ID ");
		System.out.printf("%-6s│", " 공연 장르 ");
		System.out.printf("%-8s│", " 공 연 명 ");
		System.out.printf("%-10s│", "  공연 장소 ");
		System.out.printf("%-8s│", " 공 연 일");
		System.out.printf("%-5s│", "관람 연령");
		System.out.printf("%-7s│", "  가 격");
		System.out.printf("%-5s", " 좌 석");
		System.out.println();
		System.out.println("──────────────────────────────────────────────────────────────────────────────");
		for (int i = 0; i < pList.size(); i++) {
			Performance performance = pList.get(i);
			System.out.printf("%-14s", performance.getPerformanceID());
			System.out.printf("%-8s", "  " + performance.getGenre());
			System.out.printf("%-9s", "  " + performance.getName());
			System.out.printf("%-10s", " " + performance.getVenue());
			System.out.printf("%-10s", "   " + performance.getDayOfPerformance());
			System.out.printf("%-8s", "   " + performance.getLimitAge() + "세");
			System.out.printf("%-9s", "  " + performance.getTicketPrice());
			System.out.printf("%-6s", "  " + performance.getSoldSeats() + "/" + performance.getTotalSeats());
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------------------------");

	}

	@Override
	public boolean isCartInPerformance(String pID, int num, String seat) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if (pID.equals(list.get(i).getPerformanceID())) {
				list.get(i).setQuantity(list.get(i).getQuantity() + num);
				list.get(i).setSeatNum(list.get(i).getSeatNum() + seat);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void insertPerformance(Performance p, int num, String seat) {
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
		for (int i = 0; i < str.length; i++) {
			String s = str[i].substring(1);
			x = (str[i].charAt(0)) - 65;
			y = (Integer.parseInt(s)) - 1;
			list.get(numId).getItem().getSeat()[x][y] = 0;
		}
		list.remove(numId);
	}

	@Override
	public void deletePerformance(ArrayList<Performance> list1) {
		int x = 0;
		int y = 0;
		int sum = 0;
		try {
			for (int i = 0; i < list.size(); i++) {
				String dStr = "";
				dStr = list.get(i).getSeatNum();
				String[] str = dStr.split(",");
				for (int k = 0; k < list.get(i).getQuantity(); k++) {
					String s = str[k].substring(1);
					x = (str[k].charAt(0)) - 65;
					y = (Integer.parseInt(s)) - 1;
					list.get(i).getItem().getSeat()[x][y] = 0;
				}
				for (int j = 0; j < list1.size(); j++) {
					if (list.get(i).getItem().getPerformanceID().equals(list1.get(j).getPerformanceID())) {
						list1.get(j).setSoldSeats((list1.get(j).getSoldSeats()) - (list.get(i).getQuantity()));
					}
				}
			}
			list.clear();
		} catch (Exception e) {
			System.out.println("삭제함수 오류");
			e.printStackTrace();
		}
	}


	public void printCart() {
		System.out.println("──────────────────────────── 예 매 내 역 보기 ─────────────────────────────");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("   공 연 ID    │ 공 연 명  │  공연 장소   │ 공 연 일 │  좌 석  │  수 량  │ 합 계  ");
		System.out.println("────────────────────────────────────────────────────────────────────────");

		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%-16s", list.get(i).getPerformanceID());
			System.out.printf("%-9s", "  " + list.get(i).getPerformanceName());
			System.out.printf("%-10s", "  " + list.get(i).getPerformanceVenue());
			System.out.printf("%-8s", " " + list.get(i).getPerformanceDay());
			System.out.printf("%-8s", " " + list.get(i).getSeatNum());
			System.out.printf("%-6s", "  " + list.get(i).getQuantity());
			System.out.printf("%-8s", " " + list.get(i).getTotalPrice());
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------");
	}

	public void printPaymentCart() {
		System.out.println("──────────────────────────── 결 제 내 역 보기 ─────────────────────────────");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("   공 연 ID    │ 공 연 명  │  공연 장소   │ 공 연 일 │  좌 석  │  수 량  │ 합 계  ");
		System.out.println("────────────────────────────────────────────────────────────────────────");

		for (int i = 0; i < paymentItemList.size(); i++) {
			System.out.printf("%-16s", paymentItemList.get(i).getPerformanceID());
			System.out.printf("%-9s", "  " + paymentItemList.get(i).getPerformanceName());
			System.out.printf("%-10s", "  " + paymentItemList.get(i).getPerformanceVenue());
			System.out.printf("%-8s", " " + paymentItemList.get(i).getPerformanceDay());
			System.out.printf("%-8s", " " + paymentItemList.get(i).getSeatNum());
			System.out.printf("%-6s", "  " + paymentItemList.get(i).getQuantity());
			System.out.printf("%-8s", " " + paymentItemList.get(i).getTotalPrice());
			System.out.println("  ");
		}
		System.out.println("-----------------------------------------------------------------------");

	}

	// 자동 승급 기능
	public ArrayList<Customer> changeCutomer(ArrayList<Customer> list, Customer cus) {
		boolean flag = false;
		if (cus.getAccumulatedPayment() > GOLD_CUT && cus.getAccumulatedPayment() < VIP_CUT) {
			GoldCustomer g = new GoldCustomer(cus);
			list.add(g);
			flag = true;
		}
		if (cus.getAccumulatedPayment() > VIP_CUT) {
			VIPCustomer v = new VIPCustomer(cus);
			list.add(v);
			flag = true;
		}
		if (flag) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(cus)) {
					list.remove(i);
				}
			}
		}
		if (flag == false) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId().equals(cus.getId())) {
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
