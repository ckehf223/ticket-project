package project.member;

import java.io.Serializable;

public class VIPCustomer extends Customer implements Serializable{
	private double saleRatio;//할인율
	
	
	public VIPCustomer() {
		this.setGrade("VIP");
		this.setMileageSale(0.08);
	}

	public VIPCustomer(Customer cus) {
		this.setName(cus.getName());
		this.setAge(cus.getAge());
		this.setPhone(cus.getPhone());
		this.setAddress(cus.getAddress());
		this.setId(cus.getId());
		this.setPassword(cus.getPassword());
		this.setAccumulatedPayment(cus.getAccumulatedPayment());
		this.setMileage(cus.getMileage());
		this.setGrade("VIP");
		this.saleRatio = 0.1;
		this.setMileageSale(0.08);
	}
	
	public double getSaleRatio() {
		return saleRatio;
	}

	public void setSaleRatio(double saleRatio) {
		this.saleRatio = saleRatio;
	}
	
	
}
