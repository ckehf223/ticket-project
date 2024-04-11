package project.member;

import java.io.Serializable;

public class GoldCustomer extends Customer implements Serializable{
	private double saleRatio; //할인율
	private double mileageSale; //적립율
	
	public GoldCustomer() {
		this.saleRatio = 0.05;
		this.mileageSale = 0.06;
		this.setGrade("GOLD");
	}
	
	public GoldCustomer(Customer cus) {
		this.setName(cus.getName());
		this.setAge(cus.getAge());
		this.setPhone(cus.getPhone());
		this.setAddress(cus.getAddress());
		this.setId(cus.getId());
		this.setPassword(cus.getPassword());
		this.setAccumulatedPayment(cus.getAccumulatedPayment());
		this.setMileage(cus.getMileage());
		this.setGrade("GOLD");
		this.saleRatio = 0.05;
		this.mileageSale = 0.06;
	}

	public double getSaleRatio() {
		return saleRatio;
	}
	public void setSaleRatio(double saleRatio) {
		this.saleRatio = saleRatio;
	}

	public double getMileageSale() {
		return mileageSale;
	}

	public void setMileageSale(double mileageSale) {
		this.mileageSale = mileageSale;
	}
	
	
	
}
