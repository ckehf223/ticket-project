package project.member;

import java.io.Serializable;

public class Admin extends Customer implements Serializable{

	private String adminID = "Admin";
	private int adminPW = 99999;
	
	public Admin() {
		this.adminID = "Admin";
		this.adminPW = 99999;
		
	}
	public String getAdminID() {
		return adminID;
	}
	public int getAdminPW() {
		return adminPW;
	}

}
