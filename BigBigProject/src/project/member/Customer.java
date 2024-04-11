package project.member;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable{
	private String name; 				// 이름
	private String phone; 				// 전화번호
	private String address; 			// 주소
	private String id; 					// 아이디
	private int password; 				// 비밀번호
	private int age;					// 나이
	private double saleRatio = 0.0;	    // 할인율
	private String grade ="SILVER"; 	// 등급
	private int accumulatedPayment; 	// 누적금액
	private int mileage;				// 적립마일리지
	private double mileageSale = 0.03;  // 마일리지 할인율

	public Customer() {
		
	}

	public Customer(String id, int password) {
		this.id = id;
		this.password = password;
	}

	public Customer(String name, int age, String phone, String address, String id, int password) {
		super();
		this.phone = phone;
		this.name = name;
		this.age = age;
		this.address = address;
		this.id = id;
		this.password = password;
	
	}

	public Customer(String name, String phone, String address, String id, int password, int age, double saleRatio,
			String grade, int accumulatedPayment, int mileage, double mileageSale) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.id = id;
		this.password = password;
		this.age = age;
		this.saleRatio = saleRatio;
		this.grade = grade;
		this.accumulatedPayment = accumulatedPayment;
		this.mileage = mileage;
		this.mileageSale = mileageSale;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public int getAccumulatedPayment() {
		return accumulatedPayment;
	}

	public void setAccumulatedPayment(int accumulatedPayment) {
		this.accumulatedPayment = accumulatedPayment;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public double getMileageSale() {
		return mileageSale;
	}

	public void setMileageSale(double mileageSale) {
		this.mileageSale = mileageSale;
	}

	public double getSaleRatio() {
		return saleRatio;
	}

	public void setSaleRatio(double saleRatio) {
		this.saleRatio = saleRatio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.password,this.grade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(id, other.id) && this.password == other.password && Objects.equals(this.grade ,other.grade);
	}

	public String printInfo() {
		return "[ "+ name + ", " +age+", "+ phone + ", " + address + ", " + grade + ", " + mileage + "]";
	}
	@Override
	public String toString() {
		return "[ "+ name + ", " + phone + ", " + address + ", " + id + ", "
				+ password + ", " + age + ", " + grade + ", " + accumulatedPayment
				+ ", " + mileage + "]";
	}
	
}
