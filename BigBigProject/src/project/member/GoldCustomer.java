package project.member;

import java.io.Serializable;

public class GoldCustomer extends Customer implements Serializable{

	
	public GoldCustomer() {
		
	}

	public GoldCustomer(String name, int age, String phone, String address, String id, int password) {
		super(name, age, phone, address, id, password);
	}

	public GoldCustomer(String id, int password) {
		super(id, password);
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
		this.setSaleRatio(0.05);
		this.setMileageSale(0.06);
	}
	
	
	
	
}
