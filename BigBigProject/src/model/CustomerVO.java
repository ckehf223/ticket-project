package model;

import java.util.Objects;

import project.member.Customer;

public class CustomerVO {
	private int ct_no;					// 번호
	private String ct_id; 				// 아이디
	private int ct_pw; 					// 비밀번호
	private String ct_name; 			// 이름
	private int ct_age;					// 나이
	private String ct_phone; 			// 전화번호
	private String ct_address; 			// 주소
	private String ct_grade; 			// 등급
	private double ct_saleRatio;	    // 할인율
	private int ct_totalamount; 		// 누적금액
	private int ct_mileage;				// 적립마일리지
	private double ct_mileageSale;  	// 마일리지 할인율
	
	public CustomerVO() {
		super();
	}
	public CustomerVO(String ct_id, int ct_pw) {
		super();
		this.ct_id = ct_id;
		this.ct_pw = ct_pw;
	}
	
	public CustomerVO(String ct_id, int ct_pw, String ct_name, int ct_age, String ct_phone, String ct_address) {
		super();
		this.ct_id = ct_id;
		this.ct_pw = ct_pw;
		this.ct_name = ct_name;
		this.ct_age = ct_age;
		this.ct_phone = ct_phone;
		this.ct_address = ct_address;
	}
	public CustomerVO(int ct_no, String ct_id, int ct_pw, String ct_name, int ct_age, String ct_phone,
			String ct_address, String ct_grade, double ct_saleRatio, int ct_totalamount, int ct_mileage,
			double ct_mileageSale) {
		super();
		this.ct_no = ct_no;
		this.ct_id = ct_id;
		this.ct_pw = ct_pw;
		this.ct_name = ct_name;
		this.ct_age = ct_age;
		this.ct_phone = ct_phone;
		this.ct_address = ct_address;
		this.ct_grade = ct_grade;
		this.ct_saleRatio = ct_saleRatio;
		this.ct_totalamount = ct_totalamount;
		this.ct_mileage = ct_mileage;
		this.ct_mileageSale = ct_mileageSale;
	}
	public int getCt_no() {
		return ct_no;
	}
	public void setCt_no(int ct_no) {
		this.ct_no = ct_no;
	}
	public String getCt_id() {
		return ct_id;
	}
	public void setCt_id(String ct_id) {
		this.ct_id = ct_id;
	}
	public int getCt_pw() {
		return ct_pw;
	}
	public void setCt_pw(int ct_pw) {
		this.ct_pw = ct_pw;
	}
	public String getCt_name() {
		return ct_name;
	}
	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}
	public int getCt_age() {
		return ct_age;
	}
	public void setCt_age(int ct_age) {
		this.ct_age = ct_age;
	}
	public String getCt_phone() {
		return ct_phone;
	}
	public void setCt_phone(String ct_phone) {
		this.ct_phone = ct_phone;
	}
	public String getCt_address() {
		return ct_address;
	}
	public void setCt_address(String ct_address) {
		this.ct_address = ct_address;
	}
	public String getCt_grade() {
		return ct_grade;
	}
	public void setCt_grade(String ct_grade) {
		this.ct_grade = ct_grade;
	}
	public double getCt_saleRatio() {
		return ct_saleRatio;
	}
	public void setCt_saleRatio(double ct_saleRatio) {
		this.ct_saleRatio = ct_saleRatio;
	}
	public int getCt_totalamount() {
		return ct_totalamount;
	}
	public void setCt_totalamount(int ct_totalamount) {
		this.ct_totalamount = ct_totalamount;
	}
	public int getCt_mileage() {
		return ct_mileage;
	}
	public void setCt_mileage(int ct_mileage) {
		this.ct_mileage = ct_mileage;
	}
	public double getCt_mileageSale() {
		return ct_mileageSale;
	}
	public void setCt_mileageSale(double ct_mileageSale) {
		this.ct_mileageSale = ct_mileageSale;
	}
	

	

}
