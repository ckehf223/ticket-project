package project.cart;

import java.io.Serializable;

import project.performance.Performance;

public class CartItem implements Serializable,Comparable<CartItem>{
	private Performance item;	
	private String performanceID;//comparableKey
	private String performanceName;// 공연명
	private String performanceDay; // 공연일/지역
	private String seatNum ="";	//좌석 번호
	private String performanceVenue; //장소
	private int quantity;	//인원수
	private int totalPrice;	//총구매금액
	
	public CartItem(){
		this.seatNum ="";
	}
	
	public CartItem(Performance item) {
		this.item = item;
		this.performanceID = item.getPerformanceID();
		this.performanceName= item.getName();
		this.performanceDay = item.getDayOfPerformance();
		this.performanceVenue = item.getVenue();
		this.quantity = 1;
		this.seatNum ="";
		updateTotalPrice();
		
	}
	
	public Performance getItem() {
		return item;
	}
	public void setItem(Performance item) {
		this.item = item;
		updateTotalPrice();
	}
	public String getPerformanceID() {
		return performanceID;
	}
	public void setPerformanceID(String performanceID) {
		this.performanceID = performanceID;
	}
	public String getPerformanceName() {
		return performanceName;
	}
	public void setPerformanceName(String performanceName) {
		this.performanceName = performanceName;
	}
	public String getPerformanceDay() {
		return performanceDay;
	}
	
	public void setPerformanceDay(String performanceDay) {
		this.performanceDay = performanceDay;
	}
	
	public String getPerformanceVenue() {
		return performanceVenue;
	}
	
	public void setPerformanceVenue(String performanceVenue) {
		this.performanceVenue = performanceVenue;
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		updateTotalPrice();
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void updateTotalPrice() {
		this.totalPrice = this.quantity * this.item.getTicketPrice();
	}


	@Override
	public int compareTo(CartItem o) {
		return this.performanceID.compareToIgnoreCase(o.performanceID);
	}

	
	
}
